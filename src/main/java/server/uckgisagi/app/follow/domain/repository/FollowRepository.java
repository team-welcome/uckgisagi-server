package server.uckgisagi.app.follow.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.uckgisagi.app.follow.domain.entity.Follow;

public interface FollowRepository extends JpaRepository<Follow, Long>, FollowRepositoryCustom {
}
