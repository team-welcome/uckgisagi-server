package server.uckgisagi.app.scrap.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ScrapRequest {

    @NotNull
    private Long postId;

}
