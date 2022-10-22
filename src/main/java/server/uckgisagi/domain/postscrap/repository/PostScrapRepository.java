package server.uckgisagi.domain.postscrap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.uckgisagi.domain.postscrap.entity.PostScrap;

public interface PostScrapRepository extends JpaRepository<PostScrap, Long> {
}
