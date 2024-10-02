package pdv.SendVoucherEmail.models;

import java.net.URI;

import com.google.gson.annotations.SerializedName;

public class ElectronicBill {
    private String clave;
    private Person emitter;
    private Person recipient;
    private String electronicBill;
    private URI location;
    private String errorMessages;
    @SerializedName(value = "Comers")
    private CommerceInformation comers;

    // Getters and Setters
    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Person getEmitter() {
        return emitter;
    }

    public void setEmitter(Person emitter) {
        this.emitter = emitter;
    }

    public Person getRecipient() {
        return recipient;
    }

    public void setRecipient(Person recipient) {
        this.recipient = recipient;
    }

    public String getElectronicBill() {
        return electronicBill;
    }

    public void setElectronicBill(String electronicBill) {
        this.electronicBill = electronicBill;
    }

    public URI getLocation() {
        return location;
    }

    public void setLocation(URI location) {
        this.location = location;
    }

    public String getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(String errorMessages) {
        this.errorMessages = errorMessages;
    }

    public CommerceInformation getComers() {
        return comers;
    }

    public void setComers(CommerceInformation comers) {
        this.comers = comers;
    }

    @Override
    public String toString() {
        return "Factura electronica con clave " + clave + " emitida por " + emitter + " para " + recipient;
    }
}