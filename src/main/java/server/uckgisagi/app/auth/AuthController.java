package server.uckgisagi.app.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.uckgisagi.app.auth.dto.response.GetTokenResponse;
import server.uckgisagi.app.auth.utils.AppleRestTemplate;
import server.uckgisagi.app.auth.utils.GenerateAppleKey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final GenerateAppleKey generateAppleKey;

    private final AppleRestTemplate appleRestTemplate;

    @Value("${apple.authorizationUri}")
    private String appleAuthorizeUrl;

    @Value("${apple.redirectUri}")
    private String redirectUri;

    @Value("${apple.clientId}")
    private String clientId;

    @Value("${apple.authorization-grant-type}")
    private String grantType;


    @GetMapping("/apple")
    public void setAuthApple(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(appleAuthorizeUrl +
                "&response_type=code" +
                "&client_id=" + clientId +
                "&scope=openid%20name%20email" +
                "&redirect_uri=" + redirectUri);
    }

    @PostMapping("/oauth2/redirect")
    public String getAppleRedirect(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("Uri:- " + request.getRequestURI());
        System.out.println("Code:-    " + request.getParameter("code"));
        GetTokenResponse getTokenResponse = new GetTokenResponse();
        getTokenResponse.setClientId(clientId);
        getTokenResponse.setClientSecret(generateAppleKey.generateSecretKey());
        getTokenResponse.setGrantType(grantType);
        getTokenResponse.setRedirectUri(redirectUri);
        getTokenResponse.setCode(request.getParameter("code"));
        return appleRestTemplate.getAuthTokenFromApple(getTokenResponse);

    }

}
