package pdv.SendVoucherEmail;

import com.microsoft.azure.functions.annotation.*;

import pdv.SendVoucherEmail.models.Bill;
import pdv.SendVoucherEmail.models.Recepcion;
import pdv.SendVoucherEmail.models.Enums.TipoPago;
import pdv.SendVoucherEmail.services.EmailService;
import pdv.SendVoucherEmail.services.HaciendaService;
import pdv.SendVoucherEmail.services.PDFService;
import pdv.SendVoucherEmail.util.Gson_InstantTypeAdapter;
import pdv.SendVoucherEmail.util.TipoPagoDeserializer;

import java.io.File;
import java.text.DateFormat;
import java.time.Instant;
import java.util.logging.Level;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.microsoft.azure.functions.*;

/**
 * Azure Functions with Service Bus Trigger.
 */
public class SendEmailTrigger {
    private PDFService pdfService = new PDFService();
    private EmailService emailService = new EmailService();
    private HaciendaService haciendaService = new HaciendaService();

    /**
     * This function will be invoked when a new message is received at the Service
     * Bus Queue.
          * @throws Exception 
          */
         @FunctionName("SendEmailTrigger")
         public void run(
                 @ServiceBusQueueTrigger(name = "message", queueName = "product-sendemail", connection = "PDVSBprod_SERVICEBUS") String message,
                 final ExecutionContext context) throws Exception {
        context.getLogger().info("Java Service Bus Queue trigger function executed.");
        context.getLogger().info(message);

        Gson gson = new GsonBuilder()
                .setDateFormat(DateFormat.FULL, DateFormat.FULL)
                .registerTypeAdapter(Instant.class, new Gson_InstantTypeAdapter())
                .registerTypeAdapter(TipoPago.class, new TipoPagoDeserializer()).create();

        var bill = gson.fromJson(message, Bill.class);

        File location = null;
        try {
            location = pdfService.generatePdf(bill);
        } catch (Exception e) {
            context.getLogger().log(Level.SEVERE, message, e);
            throw e;
        }
        String respuesta_xml = null;
        try {
            respuesta_xml = haciendaService.getHaciendaResponse(bill);
        } catch (Exception e) {
            context.getLogger().log(Level.SEVERE, message, e);
            throw e;
        }

        if (respuesta_xml != null && location != null) {
            var x = gson.fromJson(respuesta_xml, Recepcion.class);
            emailService.sendEmail(location, bill, Base64Decode(x.getRespuesta_xml()));
        }

        pdfService.clear(bill);
    }

    private String Base64Decode(String encodedString) {
        return new String(java.util.Base64.getDecoder().decode(encodedString));
    }
}
