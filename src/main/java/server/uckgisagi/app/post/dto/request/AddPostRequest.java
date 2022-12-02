package server.uckgisagi.app.post.dto.request;

import lombok.*;
import server.uckgisagi.app.post.domain.entity.Post;
import server.uckgisagi.app.user.domain.entity.User;

import javax.validation.constraints.NotBlank;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AddPostRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    public Post toPostEntity(User user, String imageUrl) {
        return Post.newInstance(user, imageUrl, title, content);
    }
}
