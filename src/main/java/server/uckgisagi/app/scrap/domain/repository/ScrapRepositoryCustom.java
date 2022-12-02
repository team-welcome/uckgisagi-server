package server.uckgisagi.app.scrap.domain.repository;

import server.uckgisagi.app.post.domain.entity.Post;
import server.uckgisagi.app.scrap.domain.entity.Scrap;

import java.util.List;

public interface ScrapRepositoryCustom {
    List<Post> findScrapPostByUserId(Long userId, List<Long> blockUserIds);
    boolean existsByPostAndUserId(Post post, Long userId);
    Scrap findScrapByPostIdAndUserId(Long postId, Long userId);
}
