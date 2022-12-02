package server.uckgisagi.app.review.dto.response;

import lombok.*;
import server.uckgisagi.app.review.domain.entity.Review;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewResponse {

    private Long reviewId;
    private String nickname;
    private String comment;

    @Builder(access = AccessLevel.PACKAGE)
    private ReviewResponse(final Long reviewId, final String nickname, final String comment) {
        this.reviewId = reviewId;
        this.nickname = nickname;
        this.comment = comment;
    }

    public static ReviewResponse from(Review review) {
        return ReviewResponse.builder()
                .reviewId(review.getId())
                .nickname(review.getUser().getNickname())
                .comment(review.getComment())
                .build();
    }
}
