package server.uckgisagi.app.follow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.uckgisagi.app.follow.dto.request.FollowRequest;
import server.uckgisagi.app.user.service.UserServiceUtils;
import server.uckgisagi.domain.follow.entity.Follow;
import server.uckgisagi.domain.follow.repository.FollowRepository;
import server.uckgisagi.domain.user.entity.User;
import server.uckgisagi.domain.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    @Transactional
    public User followUser(FollowRequest request, Long userId) {
        User me = UserServiceUtils.findByUserId(userRepository, userId);
        User targetUser = UserServiceUtils.findByUserId(userRepository, request.getTargetUserId());

        FollowServiceUtils.validateNotFollowingUser(followRepository, targetUser.getId(), me.getId());

        Follow followInfo = followRepository.save(Follow.newInstance(targetUser, me));
        targetUser.addFollower(followInfo);
        me.addFollowing(followInfo);

        return targetUser;
    }

    @Transactional
    public void unfollowUser(FollowRequest request, Long userId) {
        User me = UserServiceUtils.findByUserId(userRepository, userId);
        User friend = UserServiceUtils.findByUserId(userRepository, request.getTargetUserId());

        Follow followInfo = FollowServiceUtils.findByFolloweeUserIdAndFollowerUserId(followRepository, request.getTargetUserId(), userId);
        me.deleteFollowing(followInfo);
        friend.deleteFollower(followInfo);

        followRepository.delete(followInfo);
    }

}
