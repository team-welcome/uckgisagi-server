package server.uckgisagi.app.home.dto.response;

import lombok.*;

import java.util.List;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HomeUserResponse {

    private UserResponseDto myInfo;
    private List<UserResponseDto> friendInfo;

    public static HomeUserResponse of(UserResponseDto myInfo, List<UserResponseDto> friendInfo) {
        return new HomeUserResponse(myInfo, friendInfo);
    }

}
