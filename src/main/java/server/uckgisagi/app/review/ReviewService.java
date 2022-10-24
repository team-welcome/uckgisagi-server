package server.uckgisagi.app.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.uckgisagi.app.review.dto.request.AddReviewRequest;
import server.uckgisagi.app.store.service.StoreServiceUtils;
import server.uckgisagi.domain.review.repository.ReviewRepository;
import server.uckgisagi.domain.store.repository.StoreRepository;
import server.uckgisagi.domain.user.repository.UserRepository;

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
                userRepository.findById(userId).orElseThrow()
        ));
    }


}
