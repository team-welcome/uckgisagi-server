package server.uckgisagi.app.follow;

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
    public void followUser(FollowRequest request, Long userId) {
        User me = UserServiceUtils.findByUserId(userRepository, userId);
        User targetUser = UserServiceUtils.findByUserId(userRepository, request.getTargetUserId());

        followRepository.save(Follow.newInstance(targetUser, me));

        me.addFollowing(targetUser);
        targetUser.addFollower(me);
    }

    @Transactional
    public void unfollowUser(FollowRequest request, Long userId ){
        followRepository.delete(
                Follow.newInstance(
                        UserServiceUtils.findByUserId(userRepository, request.getTargetUserId()),
                        UserServiceUtils.findByUserId(userRepository, userId))
        );
    }

}
