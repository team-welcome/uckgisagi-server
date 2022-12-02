package server.uckgisagi.app.review.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.uckgisagi.app.review.domain.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
