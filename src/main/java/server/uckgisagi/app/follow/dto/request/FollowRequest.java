package server.uckgisagi.app.follow.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FollowRequest {

    @NotNull
    private Long targetUserId;

    public static FollowRequest fromTest(final Long targetUserId) {
        return new FollowRequest(targetUserId);
    }

}
