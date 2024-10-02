package pdv.SendVoucherEmail.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.jsoup.Jsoup;
import org.xhtmlrenderer.layout.SharedContext;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.jsoup.nodes.Document;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;

import pdv.SendVoucherEmail.models.Bill;
import pdv.SendVoucherEmail.models.Invoice;
import pdv.SendVoucherEmail.models.Enums.TipoPago;
import pdv.SendVoucherEmail.util.CustomElementFactoryImpl;
import pdv.SendVoucherEmail.util.MathHelper;

public class PDFService {

    private static final String PATTERN_FORMAT = "dd/MM/YYYY hh:mm:ss a";

    private CustomElementFactoryImpl customElementFactoryImpl = new CustomElementFactoryImpl();

    public File generatePdf(Bill bill) throws Exception {
        return generateHtmlToPdf(bill);
    }

    private File generateHtmlToPdf(Bill bill) throws Exception {
        Document inputHtml = Jsoup.parse(this.getClass().getResourceAsStream("/invoice.html"), "UTF-8", "");

        inputHtml.outputSettings()
                .syntax(Document.OutputSettings.Syntax.xml);

        File outputPdf = new File(System.getProperty("java.io.tmpdir") + bill.getElectronicBill().getClave() + "_Recibo.pdf");
        xhtmlToPdf(inputHtml, outputPdf, bill);
        return outputPdf;
    }

    private void xhtmlToPdf(Document xhtml, File outputPdf, Bill bill) throws Exception {
        try (OutputStream outputStream = new FileOutputStream(outputPdf)) {
            ITextRenderer renderer = new ITextRenderer();
            SharedContext sharedContext = renderer.getSharedContext();
            sharedContext.setPrint(true);
            sharedContext.setInteractive(false);
            sharedContext.setReplacedElementFactory(customElementFactoryImpl);
            String HTMLWitData = addDataToTemplate(xhtml.html(), bill);
            renderer.setDocumentFromString(HTMLWitData);
            renderer.layout();
            renderer.createPDF(outputStream);
        }
    }

    private String addDataToTemplate(String html, Bill bill) throws IOException {
        Handlebars handlebars = new Handlebars();
        handlebars.registerHelper("math", new MathHelper());
        Template template = handlebars.compileInline(html);
        String metodoPago = getMetodoPago(bill.getMedioPago().get(0));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_FORMAT)
                .withLocale(Locale.forLanguageTag("es_CR"))
                .withZone(ZoneId.of("America/Costa_Rica"));
        Invoice invoice = new Invoice(bill.getConsecutive(), bill.getElectronicBill().getClave(),
                formatter.format(bill.getDate()), bill.getElectronicBill().getComers().getName(),
                bill.getElectronicBill().getComers().getAddress(), bill.getElectronicBill().getComers().getPhone(),
                bill.getElectronicBill().getComers().getEmail(),
                bill.getElectronicBill().getRecipient().getName(), bill.getElectronicBill().getRecipient().getEmail(),
                bill.getElectronicBill().getRecipient().getNumeroIdentificacion(), metodoPago,
                bill.getProducts(),
                bill.getTotal());

        return template.apply(invoice);
    }

    private String getMetodoPago(TipoPago tipoPago) {
        return switch (tipoPago) {
            case Transferencia_DepositoBancario -> "Transferencia o Deposito Bancario";
            case Tarjeta -> "Tarjeta";
            case Efectivo -> "Efectivo";
        };
    }

    public void clear(Bill bill) {
        File file = new File(System.getProperty("java.io.tmpdir") + bill.getElectronicBill().getClave() + "_Recibo.pdf");
        file.delete();
    }
}
