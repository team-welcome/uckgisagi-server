package server.uckgisagi.app.follow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.uckgisagi.app.user.domain.dictionary.UserDictionary;
import server.uckgisagi.app.user.service.UserServiceUtils;
import server.uckgisagi.app.follow.domain.entity.Follow;
import server.uckgisagi.app.follow.domain.repository.FollowRepository;
import server.uckgisagi.app.user.domain.entity.User;
import server.uckgisagi.app.user.domain.repository.UserRepository;

import java.util.List;

// FIXME: 2022/11/18 user 연관관계 메서드 오류
@Service
@RequiredArgsConstructor
public class FollowService {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    @Transactional
    public UserDictionary followUser(Long targetUserId, Long userId) {
        User me = UserServiceUtils.findByUserId(userRepository, userId);
        User targetUser = UserServiceUtils.findByUserId(userRepository, targetUserId);

        FollowServiceUtils.validateNotFollowingUser(followRepository, targetUser.getId(), me.getId());

        followRepository.save(Follow.newInstance(targetUser, me));
//        targetUser.addFollower(followInfo);
//        me.addFollowing(followInfo);

        return UserDictionary.from(List.of(me, targetUser));
    }

    @Transactional
    public void unfollowUser(Long targetUserId, Long userId) {
        UserServiceUtils.findByUserId(userRepository, userId);
        UserServiceUtils.findByUserId(userRepository, targetUserId);

//        me.deleteFollowing(followInfo);
//        friend.deleteFollower(followInfo);

        followRepository.delete(FollowServiceUtils.findByFolloweeUserIdAndFollowerUserId(followRepository, targetUserId, userId));
    }
}
