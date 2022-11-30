package server.uckgisagi.app.auth.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import server.uckgisagi.app.auth.dto.response.TokenResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = {"spring.config.location=classpath:application-test.yml"})
public class TokenServiceTest {

    @Autowired
    private CreateTokenService createTokenService;

    @Test
    @DisplayName("유저 아이디로 토큰 생성하기")
    void create_token_by_userId() {
        final Long USER_ID = 1L;

        TokenResponse tokenInfo = createTokenService.createTokenInfo(USER_ID);
        System.out.println(tokenInfo.getAccessToken());
        System.out.println(tokenInfo.getRefreshToken());

        assertAll(
                () -> assertThat(tokenInfo).isNotNull()
        );
    }
}
