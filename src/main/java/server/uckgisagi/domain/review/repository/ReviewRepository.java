package server.uckgisagi.domain.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.uckgisagi.domain.review.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
