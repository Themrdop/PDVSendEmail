package pdv.SendVoucherEmail.models;

public class HaciendaConfig {
    private String username;
    private String password;
    private String jwt_endpoint;
    private String client_id;

    public HaciendaConfig() {
        this.username = System.getenv("HaciendaUserName");
        this.password = System.getenv("HaciendaPassword");
        this.jwt_endpoint = System.getenv("JWT_Endpoint");
        this.client_id = System.getenv("clientId");
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getJwt_endpoint() {
        return jwt_endpoint;
    }

    public String getClient_id() {
        return client_id;
    }
}