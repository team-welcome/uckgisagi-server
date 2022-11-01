package server.uckgisagi.app.auth.dto.response;

import lombok.*;
import server.uckgisagi.domain.user.entity.User;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginResponse {

    private Long userId;
    private String nickname;
    private String accessToken;
    private String refreshToken;

    public static LoginResponse of(User user, TokenResponse token) {
        return new LoginResponse(user.getId(), user.getNickname(), token.getAccessToken(), token.getRefreshToken());
    }

}
