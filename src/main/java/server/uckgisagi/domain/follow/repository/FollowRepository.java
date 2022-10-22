package server.uckgisagi.domain.follow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.uckgisagi.domain.follow.entity.Follow;

public interface FollowRepository extends JpaRepository<Follow, Long> {
}
