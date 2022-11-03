package server.uckgisagi.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import server.uckgisagi.app.follow.dto.request.FollowRequest;
import server.uckgisagi.app.follow.service.FollowService;
import server.uckgisagi.common.exception.custom.ConflictException;
import server.uckgisagi.common.exception.custom.NotFoundException;
import server.uckgisagi.domain.follow.entity.Follow;
import server.uckgisagi.domain.follow.repository.FollowRepository;
import server.uckgisagi.domain.user.entity.User;
import server.uckgisagi.domain.user.entity.enumerate.SocialType;
import server.uckgisagi.domain.user.repository.UserRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
public class FollowServiceTest {

    @Autowired
    private FollowService followService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FollowRepository followRepository;

    @AfterEach
    void clean() {
        followRepository.deleteAllInBatch();
        userRepository.deleteAllInBatch();
    }

    @Test
    void 유저_팔로우하기_성공() {
        final int FIRST = 0;
        final int SECOND = 1;
        // given
        List<User> users = userRepository.saveAll(List.of(
                User.newInstance("FirstSocialId", SocialType.APPLE, "첫번째"),
                User.newInstance("SecondSocialId", SocialType.APPLE, "두번째")
        ));
        User user = users.get(FIRST);
        User target = users.get(SECOND);

        FollowRequest request = FollowRequest.fromTest(target.getId());

        // when
        User followUser = followService.followUser(request, user.getId());

        // then
        assertAll(
                () -> assertThat(followUser).isNotNull(),
                () -> assertThat(followUser.getFollowers()).isNotNull(),
                () -> assertThat(followUser.getFollowings()).isEmpty(),
                () -> assertThat(user.getFollowers()).isEmpty(),
                () -> assertThat(user.getFollowings()).isNotNull(),
                () -> assertEquals(followUser.getFollowers().get(FIRST), user.getFollowings().get(FIRST)),
                () -> assertEquals(
                        followUser.getFollowers().get(FIRST)
                                .getFollower().getId(),
                        user.getId()
                ),
                () -> assertEquals(
                        user.getFollowings().get(FIRST)
                                .getFollowee().getId(),
                        followUser.getId()
                )
        );
    }

    @Test
    void 이미_팔로우한_유저를_팔로우하려는_경우_실패() {
        final int FIRST = 0;
        final int SECOND = 1;

        List<User> users = userRepository.saveAll(List.of(
                User.newInstance("FirstSocialId", SocialType.APPLE, "첫번째"),
                User.newInstance("SecondSocialId", SocialType.APPLE, "두번째")
        ));
        User user = users.get(FIRST);
        User target = users.get(SECOND);

        Follow followInfo = followRepository.save(Follow.newInstance(target, user));
        target.addFollower(followInfo);
        user.addFollowing(followInfo);

        FollowRequest request = FollowRequest.fromTest(target.getId());

        assertThrows(ConflictException.class, () -> followService.followUser(request, user.getId()));
    }

    @Test
    void 유저_언팔로우하기_성공() {
        final int FIRST = 0;
        final int SECOND = 1;
        // given
        List<User> users = userRepository.saveAll(List.of(
                User.newInstance("FirstSocialId", SocialType.APPLE, "첫번째"),
                User.newInstance("SecondSocialId", SocialType.APPLE, "두번째")
        ));
        User user = users.get(FIRST);
        User friend = users.get(SECOND);

        Follow followInfo = followRepository.save(Follow.newInstance(friend, user));
        friend.addFollower(followInfo);
        user.addFollowing(followInfo);

        FollowRequest request = FollowRequest.fromTest(friend.getId());

        // when
        System.out.println("=============== UnFollow ================");
        followService.unfollowUser(request, user.getId());

        // then
        System.out.println("=============== THEN ================");
        Follow follow = followRepository.findFollowByFolloweeUserIdAndFollowerUserId(friend.getId(), user.getId());
        assertAll(
                () -> assertThat(follow).isNull(),
                () -> assertThat(friend.getFollowers()).isEmpty(),
                () -> assertThat(friend.getFollowers()).isEmpty()
        );
    }

    @Test
    void 이미_언팔로우한_유저를_언팔로우_할때_실패() {
        final int FIRST = 0;
        final int SECOND = 1;
        List<User> users = userRepository.saveAll(List.of(
                User.newInstance("FirstSocialId", SocialType.APPLE, "첫번째"),
                User.newInstance("SecondSocialId", SocialType.APPLE, "두번째")
        ));
        User user = users.get(FIRST);
        User friend = users.get(SECOND);

        FollowRequest request = FollowRequest.fromTest(friend.getId());

        assertThrows(NotFoundException.class, () -> followService.unfollowUser(request, user.getId()));
    }

}