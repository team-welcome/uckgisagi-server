package server.uckgisagi.domain.follow.repository;

import server.uckgisagi.domain.follow.entity.Follow;
import server.uckgisagi.domain.user.entity.User;

import java.util.List;

public interface FollowRepositoryCustom {
    boolean existsByTargetUserIdAndUserId(Long targetUserId, Long userId);
    List<User> findMyFollowingUserByUserId(Long userId);
    Follow findFollowByFolloweeUserIdAndFollowerUserId(Long followeeUserId, Long followerUserId);
}
