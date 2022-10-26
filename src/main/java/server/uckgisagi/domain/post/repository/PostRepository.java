package server.uckgisagi.domain.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.uckgisagi.domain.post.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {
}
