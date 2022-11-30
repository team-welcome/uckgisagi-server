package server.uckgisagi.domain.post.repository;

import server.uckgisagi.domain.post.entity.Post;
import server.uckgisagi.domain.user.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface PostRepositoryCustom {
    Post findPostByPostId(Long postId);
    List<Post> findPostByUserId(Long userId);
    boolean existsByTodayDate(LocalDate today, Long userId);
    List<User> findUserIdsByTodayDate(LocalDate today, List<Long> userIds);
    Post findByPostIdAndUserId(Long postId, Long userId);
    List<Post> findAllByPostStatus(User loginUser);
}
