package server.uckgisagi.app.auth.dto.response;

import lombok.Data;

@Data
public class AppleIdTokenResponse {

    public String access_token;
    public String token_type;
    public String expires_in;
    public String refresh_token;
    public String id_token;

    @Override
    public String toString() {
        return "AppleIdTokenResponse{" +
                "access_token='" + access_token + '\'' +
                ", token_type='" + token_type + '\'' +
                ", expires_in='" + expires_in + '\'' +
                ", refresh_token='" + refresh_token + '\'' +
                ", id_token='" + id_token + '\'' +
                '}';
    }
}
