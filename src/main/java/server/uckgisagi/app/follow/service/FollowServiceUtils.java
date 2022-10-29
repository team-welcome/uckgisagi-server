package server.uckgisagi.app.follow.service;

import org.jetbrains.annotations.NotNull;
import server.uckgisagi.common.exception.custom.NotFoundException;
import server.uckgisagi.domain.follow.entity.Follow;
import server.uckgisagi.domain.follow.repository.FollowRepository;

import static server.uckgisagi.common.exception.ErrorResponseResult.*;

public class FollowServiceUtils {

    @NotNull
    public static Follow findByFolloweeUserIdAndFollowerUserId(FollowRepository followRepository, Long followeeUserId, Long followerUserId) {
        Follow follow = followRepository.findFollowByFolloweeUserIdAndFollowerUserId(followeeUserId, followerUserId);
        if (follow == null) {
            throw new NotFoundException(String.format("존재하지 않는 팔로우 대상 - 팔로워 (%s - %s) 관계 입니다", followeeUserId, followerUserId), NOT_FOUND_FOLLOW_RELATION_EXCEPTION);
        }
        return follow;
    }
}
