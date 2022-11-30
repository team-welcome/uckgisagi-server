package server.uckgisagi.app.review.dto.request;

import lombok.*;
import server.uckgisagi.domain.review.entity.Review;
import server.uckgisagi.domain.store.entity.Store;
import server.uckgisagi.domain.user.entity.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(builderMethodName = "testBuilder")
public class AddReviewRequest {

    @NotNull
    private Long storeId;

    @NotBlank
    private String content;

    public Review toReviewEntity(Store store, User user) {
        Review review = Review.newInstance(store, user, content);
        store.addReview(review);
        return review;
    }
}
