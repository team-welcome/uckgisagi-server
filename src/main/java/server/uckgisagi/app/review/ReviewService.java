package server.uckgisagi.app.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.uckgisagi.app.review.dto.request.AddReviewRequest;
import server.uckgisagi.app.review.dto.response.ReviewResponse;
import server.uckgisagi.app.store.service.StoreServiceUtils;
import server.uckgisagi.app.user.service.UserServiceUtils;
import server.uckgisagi.app.review.domain.repository.ReviewRepository;
import server.uckgisagi.app.store.domain.repository.StoreRepository;
import server.uckgisagi.app.user.domain.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    @Transactional
    public void addReview(AddReviewRequest request, Long userId) {
        reviewRepository.save(request.toReviewEntity(
                StoreServiceUtils.findByStoreId(storeRepository, request.getStoreId()),
                UserServiceUtils.findByUserId(userRepository, userId)
        ));
    }

    @Transactional(readOnly = true)
    public List<ReviewResponse> retrieveReview(Long storeId) {
        return StoreServiceUtils.findByStoreId(storeRepository, storeId)
                .getReviews().stream()
                .map(ReviewResponse::from)
                .collect(Collectors.toList());
    }
}
