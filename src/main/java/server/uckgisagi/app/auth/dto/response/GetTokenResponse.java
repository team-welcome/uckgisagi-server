package server.uckgisagi.app.auth.dto.response;

import lombok.Data;

@Data
public class GetTokenResponse {
    public String clientId;
    public String clientSecret;
    public String code;
    public String grantType;
    public String redirectUri;
}
