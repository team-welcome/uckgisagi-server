package server.uckgisagi.domain.scrap.repository;

import server.uckgisagi.domain.post.entity.Post;

import java.util.List;

public interface ScrapRepositoryCustom {
    List<Post> findScrapPostByUserId(Long userId);
    boolean existsByPostAndUserId(Post post, Long userId);
}
