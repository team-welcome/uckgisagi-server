package server.uckgisagi.app.post.dto.response;

import lombok.*;
import server.uckgisagi.common.dto.AuditingTimeResponse;
import server.uckgisagi.domain.post.entity.Post;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostResponse extends AuditingTimeResponse {

    private Long postId;
    private String imageUrl;
    private String title;
    private String content;

    @Builder(access = AccessLevel.PACKAGE)
    private PostResponse(final Long postId, final String imageUrl, final String title, final String content) {
        this.postId = postId;
        this.imageUrl = imageUrl;
        this.title = title;
        this.content = content;
    }

    public static PostResponse from(Post post) {
        PostResponse response = PostResponse.builder()
                .postId(post.getId())
                .imageUrl(post.getImageUrl())
                .title(post.getTitle())
                .content(post.getContent())
                .build();
        response.setBaseTime(post);
        return response;
    }

}
