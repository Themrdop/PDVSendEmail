package pdv.SendVoucherEmail.models;

public class CommerceInformation {
    private String name;
    private String cedula;
    private String address;
    private String phone;
    private String email;
    private String logo;
    private byte provincia;
    private byte canton;
    private byte distrito;
    private byte barrio;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public byte getProvincia() {
        return provincia;
    }

    public void setProvincia(byte provincia) {
        this.provincia = provincia;
    }

    public byte getCanton() {
        return canton;
    }

    public void setCanton(byte canton) {
        this.canton = canton;
    }

    public byte getDistrito() {
        return distrito;
    }

    public void setDistrito(byte distrito) {
        this.distrito = distrito;
    }

    public byte getBarrio() {
        return barrio;
    }

    public void setBarrio(byte barrio) {
        this.barrio = barrio;
    }
}