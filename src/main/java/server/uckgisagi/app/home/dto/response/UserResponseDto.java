package server.uckgisagi.app.home.dto.response;

import lombok.*;
import server.uckgisagi.domain.user.entity.User;
import server.uckgisagi.domain.user.entity.enumerate.UserGrade;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserResponseDto {

    private Long userId;
    private String nickname;
    private UserGrade grade;
    private TodayPostStatus postStatus;

    @Builder(access = AccessLevel.PACKAGE)
    private UserResponseDto(final Long userId, final String nickname, final UserGrade grade, final TodayPostStatus postStatus) {
        this.userId = userId;
        this.nickname = nickname;
        this.grade = grade;
        this.postStatus = postStatus;
    }

    public static UserResponseDto of(User user, TodayPostStatus postStatus) {
        return UserResponseDto.builder()
                .userId(user.getId())
                .nickname(user.getNickname())
                .grade(user.getGrade())
                .postStatus(postStatus)
                .build();
    }

}
