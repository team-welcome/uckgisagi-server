package server.uckgisagi.app.user.dto.response;

import lombok.*;
import server.uckgisagi.app.user.domain.entity.User;
import server.uckgisagi.app.user.domain.entity.enumerate.UserGrade;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SearchUserResponse {

    private Long userId;
    private String nickname;
    private UserGrade grade;
    private FollowStatus followStatus;

    @Builder(access = AccessLevel.PACKAGE)
    private SearchUserResponse(Long userId, String nickname, UserGrade grade, FollowStatus followStatus) {
        this.userId = userId;
        this.nickname = nickname;
        this.grade = grade;
        this.followStatus = followStatus;
    }

    public static SearchUserResponse of(User user, FollowStatus followStatus) {
        return SearchUserResponse.builder()
                .userId(user.getId())
                .nickname(user.getNickname())
                .grade(user.getGrade())
                .followStatus(followStatus)
                .build();
    }

}
