package server.uckgisagi.app.auth.provider;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import server.uckgisagi.app.auth.service.AuthService;
import server.uckgisagi.app.auth.service.impl.AppleAuthService;
import server.uckgisagi.domain.user.entity.enumerate.SocialType;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AuthServiceProvider {

    private static final Map<SocialType, AuthService> authServiceMap = new HashMap<>();

    private final AppleAuthService appleAuthService;

    @PostConstruct // 의존성 주입이 완료된 후 초기화
    public void initAuthServiceMap() {
        authServiceMap.put(SocialType.APPLE, appleAuthService);
    }

    public AuthService getAuthService(SocialType socialType) {
        return authServiceMap.get(socialType);
    }

}
