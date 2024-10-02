package pdv.SendVoucherEmail.models;

import com.google.gson.annotations.SerializedName;

public class Recepcion {
    private String clave;
    private String fecha;
    @SerializedName(value = "ind-estado")
    private String ind_estado;
    @SerializedName(value = "respuesta-xml")
    private String respuesta_xml;

    // Getters and Setters
    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getInd_estado() {
        return ind_estado;
    }

    public void setInd_estado(String ind_estado) {
        this.ind_estado = ind_estado;
    }

    public String getRespuesta_xml() {
        return respuesta_xml;
    }

    public void setRespuesta_xml(String respuesta_xml) {
        this.respuesta_xml = respuesta_xml;
    }
}
