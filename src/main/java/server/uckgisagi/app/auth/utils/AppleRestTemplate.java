package server.uckgisagi.app.auth.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import server.uckgisagi.app.auth.dto.response.AppleIdTokenResponse;
import server.uckgisagi.app.auth.dto.response.GetTokenResponse;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Component
@RequiredArgsConstructor
public class AppleRestTemplate {

    private final GenerateAppleKey generateAppleKey;

    @Value("${apple.token-uri}")
    private String tokenUrl;

    @Value("${apple.jwkSetUri}")
    private String jwkSetUrl;

    public String getAuthTokenFromApple(GetTokenResponse response) throws JsonProcessingException, InvalidKeySpecException, NoSuchAlgorithmException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/x-www-form-urlencoded");
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("client_id", response.getClientId());
        parameters.add("client_secret", response.getClientSecret());
        parameters.add("code", response.getCode());
        parameters.add("grant_type", response.getGrantType());
        parameters.add("redirect_uri", response.getRedirectUri());
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(parameters, headers);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(tokenUrl, entity, String.class);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            AppleIdTokenResponse appleIdTokenResponse = new ObjectMapper().readValue(responseEntity.getBody(), AppleIdTokenResponse.class);
            System.out.println(appleIdTokenResponse.id_token);
            return getUserInfo(appleIdTokenResponse);
        }
        return "Failed to Auth Token";
    }

    private String getUserInfo(AppleIdTokenResponse appleIdTokenResponse) throws InvalidKeySpecException, NoSuchAlgorithmException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Key> responseEntity = restTemplate.getForEntity(jwkSetUrl, Key.class);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            System.out.println(responseEntity.getBody().getKeys().size());
            String email = generateAppleKey.createPublicKeyApple(appleIdTokenResponse, responseEntity.getBody().getKeys());
            return email;
        }
        return "Email id Not Found";
    }
}
