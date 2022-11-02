package server.uckgisagi.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import server.uckgisagi.domain.follow.entity.Follow;
import server.uckgisagi.domain.follow.repository.FollowRepository;
import server.uckgisagi.domain.user.entity.User;
import server.uckgisagi.domain.user.entity.enumerate.SocialType;
import server.uckgisagi.domain.user.repository.UserRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

//@DataJpaTest
//@Import(TestConfig.class)
@SpringBootTest
public class FollowRepositoryTest {

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
    @Transactional
    void 내가_팔로우하는_유저_레포지토리_테스트() {
        // given
        User first = User.newInstance("firstSocialId", SocialType.APPLE, "FirstNickname");
        User secondTarget = User.newInstance("secondSocialId", SocialType.APPLE, "SecondNickname");
        User thirdTarget = User.newInstance("thirdSocialId", SocialType.APPLE, "ThirdNickname");
        userRepository.saveAll(List.of(first, secondTarget, thirdTarget));

        Follow follow = Follow.newInstance(secondTarget, first);
        Follow follow1 = Follow.newInstance(thirdTarget, first);
        followRepository.saveAll(List.of(follow, follow1));

        first.addFollowing(follow);
        first.addFollowing(follow1);

        secondTarget.addFollower(follow);
        thirdTarget.addFollower(follow1);

        // when
        System.out.println("================= findMyFollowingUserByUserId =================");
        /**
         * inner join 쿼리 한방
         */
        List<User> joinQuery = followRepository.findMyFollowingUserByUserId(first.getId());

        System.out.println("================= getMyFollowing =================");
        /**
         * 쿼리 안날아감
         */
        List<User> noQuery = first.getMyFollowings();

        // then
        assertAll(
                () -> assertThat(joinQuery).isNotEmpty(),
                () -> assertThat(noQuery).isNotEmpty(),
                () -> {
                    joinQuery.forEach(user -> {
                        boolean contains = noQuery.contains(user);
                        assertTrue(contains);
                    });
                }
        );
    }

}
