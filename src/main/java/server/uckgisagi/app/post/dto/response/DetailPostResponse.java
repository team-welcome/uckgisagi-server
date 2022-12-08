package server.uckgisagi.app.post.dto.response;

import lombok.*;
import server.uckgisagi.common.dto.AuditingTimeResponse;
import server.uckgisagi.app.post.domain.entity.Post;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DetailPostResponse extends AuditingTimeResponse {

    private Long postId;
    private Long userId;
    private String nickname;
    private String imageUrl;
    private String content;
    private ScrapStatus scrapStatus;

    @Builder(access = AccessLevel.PACKAGE)
    private DetailPostResponse(final Long postId, final Long userId, final String nickname, final String imageUrl,
                               final String content, final ScrapStatus scrapStatus) {
        this.postId = postId;
        this.userId = userId;
        this.nickname = nickname;
        this.imageUrl = imageUrl;
        this.content = content;
        this.scrapStatus = scrapStatus;
    }

    public static DetailPostResponse of(Post post, ScrapStatus scrapStatus) {
        DetailPostResponse response = DetailPostResponse.builder()
                .postId(post.getId())
                .userId(post.getUser().getId())
                .nickname(post.getUser().getNickname())
                .imageUrl(post.getImageUrl())
                .content(post.getContent())
                .scrapStatus(scrapStatus)
                .build();
        response.setBaseTime(post);
        return response;
    }
}
