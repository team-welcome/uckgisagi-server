package server.uckgisagi.domain.scrap.repository;

import server.uckgisagi.domain.post.entity.Post;
import server.uckgisagi.domain.scrap.entity.Scrap;
import server.uckgisagi.domain.user.entity.User;

import java.util.List;

public interface ScrapRepositoryCustom {
    List<Post> findScrapPostByUserId(Long userId, User loginUser);
    boolean existsByPostAndUserId(Post post, Long userId);
    Scrap findScrapByPostIdAndUserId(Long postId, Long userId);
}
