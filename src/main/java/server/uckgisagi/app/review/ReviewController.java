package server.uckgisagi.app.review;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import server.uckgisagi.app.review.dto.request.AddReviewRequest;
import server.uckgisagi.app.review.dto.response.ReviewResponse;
import server.uckgisagi.common.dto.ApiSuccessResponse;
import server.uckgisagi.common.success.SuccessResponseResult;
import server.uckgisagi.config.interceptor.Auth;
import server.uckgisagi.config.resolver.LoginUserId;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

import static server.uckgisagi.common.success.SuccessResponseResult.*;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @ApiOperation("[인증] 리필 스테이션 상세보기 페이지 - 후기 등록")
    @Auth
    @PostMapping("/v1/review")
    public ApiSuccessResponse<SuccessResponseResult> addReview(@Valid @RequestBody AddReviewRequest request, @ApiIgnore @LoginUserId Long userId) {
        reviewService.addReview(request, userId);
        return ApiSuccessResponse.success(CREATED_REVIEW_COMMENT);
    }

    @ApiOperation("[인증] 리필 스테이션 상세보기 페이지 - 후기 조회")
    @Auth
    @GetMapping("/v1/review/{storeId}")
    public ApiSuccessResponse<List<ReviewResponse>> retrieveStoreReview(@PathVariable Long storeId) {
        return ApiSuccessResponse.success(OK_RETRIEVE_STORE_REVIEW, reviewService.retrieveReview(storeId));
    }

}
