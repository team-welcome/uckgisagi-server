package server.uckgisagi.app.post.dto.response;

import lombok.*;
import server.uckgisagi.domain.post.entity.Post;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PreviewPostResponse {

    private Long postId;
    private String imageUrl;
    private String content;
    private ScrapStatus scrapStatus;

    @Builder(access = AccessLevel.PACKAGE)
    private PreviewPostResponse(final Long postId, final String imageUrl,
                               final String content, final ScrapStatus scrapStatus) {
        this.postId = postId;
        this.imageUrl = imageUrl;
        this.content = content;
        this.scrapStatus = scrapStatus;
    }

    public static PreviewPostResponse of(Post post, ScrapStatus scrapStatus) {
        return PreviewPostResponse.builder()
                .postId(post.getId())
                .imageUrl(post.getImageUrl())
                .content(post.getContent())
                .scrapStatus(scrapStatus)
                .build();
    }

}
