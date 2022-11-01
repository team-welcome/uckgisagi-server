package server.uckgisagi.app.auth.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenRequest {

    @NotBlank(message = "${auth.accessToken.notBlank}")
    private String accessToken;

    @NotBlank(message = "${auth.refreshToken.notBlank}")
    private String refreshToken;

}
