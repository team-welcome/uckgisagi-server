package server.uckgisagi.app.user.dto.request;

import lombok.*;
import server.uckgisagi.app.user.domain.entity.enumerate.SocialType;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUserDto {

    private String nickname;
    private String socialId;
    private SocialType socialType;
    private String fcmToken;

    public static CreateUserDto of(String nickname, String socialId, SocialType socialType, String fcmToken) {
        return new CreateUserDto(nickname, socialId, socialType, fcmToken);
    }

}
