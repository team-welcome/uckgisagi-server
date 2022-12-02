package server.uckgisagi.app.follow.domain.repository;

import server.uckgisagi.app.follow.domain.entity.Follow;
import server.uckgisagi.app.user.domain.entity.User;

import java.util.List;

public interface FollowRepositoryCustom {
    boolean existsByTargetUserIdAndUserId(Long targetUserId, Long userId);
    List<User> findMyFollowingUserByUserId(Long userId);
    Follow findFollowByFolloweeUserIdAndFollowerUserId(Long followeeUserId, Long followerUserId);
}
