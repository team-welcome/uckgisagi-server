package server.uckgisagi.app.post.domain.repository;

import server.uckgisagi.app.post.domain.entity.Post;
import server.uckgisagi.app.user.domain.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface PostRepositoryCustom {
    Post findPostByPostId(Long postId);
    List<Post> findPostByUserId(Long userId);
    boolean existsByTodayDate(LocalDate today, Long userId);
    List<User> findUserIdsByTodayDate(LocalDate today, List<Long> userIds);
    Post findByPostIdAndUserId(Long postId, Long userId);
    List<Post> findAllByPostStatus(List<Long> blockUserIds);
    List<Post> findPostByDateAndUserId(LocalDate date, Long userId);
}
