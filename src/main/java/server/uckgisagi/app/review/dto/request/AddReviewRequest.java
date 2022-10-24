package server.uckgisagi.app.review.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import server.uckgisagi.domain.review.entity.Review;
import server.uckgisagi.domain.store.entity.Store;
import server.uckgisagi.domain.user.entity.User;

import javax.validation.constraints.NotBlank;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddReviewRequest {

    @NotBlank
    private Long storeId;

    @NotBlank
    private String content;

    public Review toReviewEntity(Store store, User user) {
        return Review.newInstance(store, user, content);
    }

}
