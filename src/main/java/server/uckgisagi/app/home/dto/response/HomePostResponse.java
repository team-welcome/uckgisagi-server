package server.uckgisagi.app.home.dto.response;

import lombok.*;
import server.uckgisagi.app.post.dto.response.PostResponse;

import java.time.LocalDate;
import java.util.List;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HomePostResponse {

    private List<LocalDate> postDates;
    private List<PostResponse> posts;

    public static HomePostResponse of(List<LocalDate> postDates, List<PostResponse> posts) {
        return new HomePostResponse(postDates, posts);
    }

}
