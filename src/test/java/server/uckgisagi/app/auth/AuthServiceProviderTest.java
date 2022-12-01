package server.uckgisagi.app.auth;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import server.uckgisagi.app.auth.provider.AuthServiceProvider;
import server.uckgisagi.app.auth.service.AuthService;
import server.uckgisagi.app.auth.service.impl.AppleAuthService;
import server.uckgisagi.domain.user.entity.enumerate.SocialType;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {"spring.config.location=classpath:application-test.yml"})
public class AuthServiceProviderTest {

    @Autowired
    private AuthServiceProvider authServiceProvider;

    @DisplayName("AuthServiceProvider 가 성공적으로 등록된다")
    @Test
    void AuthServiceProvider_register_success() {
        final SocialType APPLE = SocialType.APPLE;
        AuthService authService = authServiceProvider.getAuthService(APPLE);

        assertTrue(authService instanceof AppleAuthService);
    }
}
