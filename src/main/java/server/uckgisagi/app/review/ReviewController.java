package server.uckgisagi.app.review;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import server.uckgisagi.app.review.dto.request.AddReviewRequest;
import server.uckgisagi.app.review.dto.response.ReviewResponse;
import server.uckgisagi.common.dto.ApiSuccessResponse;
import server.uckgisagi.common.success.SuccessResponseResult;

import javax.validation.Valid;
import java.util.List;

import static server.uckgisagi.common.success.SuccessResponseResult.*;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/v1/review")
    public ApiSuccessResponse<SuccessResponseResult> addReview(@Valid @RequestBody AddReviewRequest request, Long userId) {
        reviewService.addReview(request, userId);
        return ApiSuccessResponse.success(CREATED_REVIEW_COMMENT);
    }

    @GetMapping("/v1/review/{storeId}")
    public ApiSuccessResponse<List<ReviewResponse>> retrieveStoreReview(@PathVariable Long storeId) {
        return ApiSuccessResponse.success(OK_RETRIEVE_STORE_REVIEW, reviewService.retrieveReview(storeId));
    }

}
