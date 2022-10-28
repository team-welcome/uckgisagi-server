package server.uckgisagi.app.user.dto.request;

import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@ToString
@Getter
public class SearchUserRequest {

    @NotBlank
    private String nickname;

    private SearchUserRequest() {}

    private SearchUserRequest(final String nickname) {
        this.nickname = nickname;
    }

    public static SearchUserRequest fromTest(String nickname) {
        return new SearchUserRequest(nickname);
    }

}
