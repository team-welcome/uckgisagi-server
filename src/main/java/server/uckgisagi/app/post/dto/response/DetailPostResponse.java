package server.uckgisagi.app.post.dto.response;

import lombok.*;
import server.uckgisagi.common.dto.AuditingTimeResponse;
import server.uckgisagi.domain.post.entity.Post;
import server.uckgisagi.domain.user.entity.User;

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

    public static DetailPostResponse of(Post post, Long userId, String nickname, ScrapStatus scrapStatus) {
        DetailPostResponse response = DetailPostResponse.builder()
                .postId(post.getId())
                .userId(userId)
                .nickname(nickname)
                .imageUrl(post.getImageUrl())
                .content(post.getContent())
                .scrapStatus(scrapStatus)
                .build();
        response.setBaseTime(post);
        return response;
    }
}
