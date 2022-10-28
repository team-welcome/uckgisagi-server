package server.uckgisagi.app.follow.dto.request;

import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@ToString
@Getter
public class FollowRequest {

    @NotNull
    private Long targetUserId;

    private FollowRequest() {}

    private FollowRequest(final Long targetUserId) {
        this.targetUserId = targetUserId;
    }

}
