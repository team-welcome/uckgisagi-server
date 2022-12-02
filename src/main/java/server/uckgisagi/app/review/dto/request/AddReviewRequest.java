package server.uckgisagi.app.review.dto.request;

import lombok.*;
import server.uckgisagi.app.review.domain.entity.Review;
import server.uckgisagi.app.store.domain.entity.Store;
import server.uckgisagi.app.user.domain.entity.User;

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
