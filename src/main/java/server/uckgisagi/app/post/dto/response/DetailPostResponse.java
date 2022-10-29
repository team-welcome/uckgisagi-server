package server.uckgisagi.app.post.dto.response;

import lombok.*;
import server.uckgisagi.common.dto.AuditingTimeResponse;
import server.uckgisagi.domain.post.entity.Post;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DetailPostResponse extends AuditingTimeResponse {

    private Long postId;
    private String nickname;
    private String imageUrl;
    private String content;
    private ScrapStatus scrapStatus;

    @Builder(access = AccessLevel.PACKAGE)
    private DetailPostResponse(final Long postId, final String nickname, final String imageUrl,
                               final String content, final ScrapStatus scrapStatus) {
        this.postId = postId;
        this.nickname = nickname;
        this.imageUrl = imageUrl;
        this.content = content;
        this.scrapStatus = scrapStatus;
    }

    public static DetailPostResponse of(Post post, String nickname, ScrapStatus scrapStatus) {
        return DetailPostResponse.builder()
                .postId(post.getId())
                .nickname(nickname)
                .imageUrl(post.getImageUrl())
                .content(post.getContent())
                .scrapStatus(scrapStatus)
                .build();
    }

}