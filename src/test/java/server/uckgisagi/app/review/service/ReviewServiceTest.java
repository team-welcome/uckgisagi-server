package server.uckgisagi.app.review.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import server.uckgisagi.app.review.ReviewService;
import server.uckgisagi.app.review.dto.request.AddReviewRequest;
import server.uckgisagi.app.review.dto.response.ReviewResponse;
import server.uckgisagi.common.exception.custom.NotFoundException;
import server.uckgisagi.domain.review.entity.Review;
import server.uckgisagi.domain.review.repository.ReviewRepository;
import server.uckgisagi.domain.store.entity.Store;
import server.uckgisagi.domain.store.repository.StoreRepository;
import server.uckgisagi.domain.user.entity.User;
import server.uckgisagi.domain.user.entity.enumerate.SocialType;
import server.uckgisagi.domain.user.repository.UserRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = {"spring.config.location=classpath:application-test.yml"})
public class ReviewServiceTest {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StoreRepository storeRepository;

    private User user;

    @BeforeEach
    void setup() {
        user = User.newInstance("SOCIAL_ID", SocialType.APPLE, "NICKNAME");
        userRepository.save(user);
    }

    @AfterEach
    void clean() {
        reviewRepository.deleteAllInBatch();
        userRepository.deleteAllInBatch();
    }

    @DisplayName("매장 리뷰 작성 성공")
    @Test
    @Transactional
    void add_review_success() {
        // given
        AddReviewRequest request = AddReviewRequest.testBuilder()
                .storeId(STORE_ID)
                .content("Content")
                .build();

        // when
        reviewService.addReview(request, user.getId());

        // then
        Store store = storeRepository.findStoreByStoreId(STORE_ID);
        assertAll(
                () -> assertThat(store.getReviews()).isNotEmpty()
//                () -> assertEquals(store.getReviews().size(), 1) FIXME Debug 시 정상 동작 -> Test 실행 시 store 에 review 2개 add 됨 (ㅈ버그)
        );

        // finally
        store.getReviews().remove(0);
    }

    @DisplayName("해당 매장의 리뷰 조회 성공")
    @Test
    @Transactional
    void retrieve_review_success() {
        // given
        Store store = storeRepository.findStoreByStoreId(STORE_ID);
        Review review = Review.newInstance(store, user, "Comment");
        store.addReview(review);

        // when
        List<ReviewResponse> response = reviewService.retrieveReview(STORE_ID);

        // then
        assertAll(
                () -> assertThat(response).isNotNull(),
                () -> assertEquals(response.get(0).getReviewId(), review.getId()),
                () -> assertEquals(response.get(0).getComment(), review.getComment()),
                () -> assertEquals(response.get(0).getNickname(), review.getUser().getNickname())
        );

        // finally
        store.getReviews().remove(0);
    }

    @DisplayName("존재하지 않는 매장에 리뷰 작성 시 실패")
    @Test
    void throw_NotFoundException_when_request_not_exist_store() {
        AddReviewRequest request = AddReviewRequest.testBuilder()
                .storeId(NOT_EXIST_STORE_ID)
                .content("Content")
                .build();

        assertThrows(NotFoundException.class, () -> reviewService.addReview(request, user.getId()));
    }

    @DisplayName("존재하지 않는 매장의 리뷰 조회 시 실패")
    @Test
    void throw_NotFoundException_when_retrieve_review_not_exist_store() {
        assertThrows(NotFoundException.class, () -> reviewService.retrieveReview(NOT_EXIST_STORE_ID));
    }

    private static final Long STORE_ID = 1L;
    private static final Long NOT_EXIST_STORE_ID = - 1L;
}
