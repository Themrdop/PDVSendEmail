package pdv.SendVoucherEmail.services;
import java.time.Duration;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.google.gson.Gson;

import pdv.SendVoucherEmail.models.Bill;
import pdv.SendVoucherEmail.models.HaciendaConfig;
import pdv.SendVoucherEmail.models.Token;

public class HaciendaService {
    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(3);

    private HaciendaConfig haciendaConfig;

    public HaciendaService() {
        this.haciendaConfig = new HaciendaConfig();
    }

    public String getHaciendaResponse(Bill bill) throws Exception {
        String Token = getToken();
        WebClient.Builder webClientBuilder = WebClient.builder();
        return webClientBuilder.build()
                .get()
                .uri(bill.getElectronicBill().getLocation())
                .header(HttpHeaders.AUTHORIZATION, "bearer " + Token)
                .retrieve()
                .bodyToMono(String.class)
                .block(REQUEST_TIMEOUT);
    }

    private String getToken() throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> values = new LinkedMultiValueMap<>();
        values.add("client_id", haciendaConfig.getClient_id());
        values.add("grant_type", "password");
        values.add("username", haciendaConfig.getUsername());
        values.add("password", haciendaConfig.getPassword());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(values, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(haciendaConfig.getJwt_endpoint(), request, String.class);

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new Exception("Error: " + response.getBody());
        }

        return getOnlyToken(response.getBody());
    }

    private String getOnlyToken(String token) {
        var gson = new Gson();

        var objToken = gson.fromJson(token, Token.class);

        return objToken.getAccess_token();
    }
}
