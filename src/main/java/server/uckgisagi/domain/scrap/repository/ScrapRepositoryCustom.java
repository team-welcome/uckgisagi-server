package server.uckgisagi.domain.scrap.repository;

import server.uckgisagi.domain.post.entity.Post;
import server.uckgisagi.domain.scrap.entity.Scrap;

import java.util.List;

public interface ScrapRepositoryCustom {
    List<Post> findScrapPostByUserId(Long userId);
    boolean existsByPostAndUserId(Post post, Long userId);
    Scrap findScrapByPostIdAndUserId(Long postId, Long userId);
}
