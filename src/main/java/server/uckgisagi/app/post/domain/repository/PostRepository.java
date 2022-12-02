package server.uckgisagi.app.post.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.uckgisagi.app.post.domain.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {
}
