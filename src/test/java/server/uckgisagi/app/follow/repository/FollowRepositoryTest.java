package server.uckgisagi.app.follow.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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

@SpringBootTest(properties = "spring.config.location=classpath:application-test.yml")
public class FollowRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FollowRepository followRepository;

    private User master;
    private User firstTarget;
    private User secondTarget;

    @BeforeEach
    void setup() {
        master = User.newInstance("firstSocialId", SocialType.APPLE, "FirstNickname");
        firstTarget = User.newInstance("secondSocialId", SocialType.APPLE, "SecondNickname");
        secondTarget = User.newInstance("thirdSocialId", SocialType.APPLE, "ThirdNickname");

        userRepository.saveAll(List.of(master, firstTarget, secondTarget));

        Follow follow = Follow.newInstance(firstTarget, master);
        Follow follow1 = Follow.newInstance(secondTarget, master);
        followRepository.saveAll(List.of(follow, follow1));

        master.addFollowing(follow);
        firstTarget.addFollower(follow);

        master.addFollowing(follow1);
        secondTarget.addFollower(follow1);
    }

    @AfterEach
    void clean() {
        followRepository.deleteAllInBatch();
        userRepository.deleteAllInBatch();
    }

    @DisplayName("내가_팔로우하는_유저_레포지토리_테스트")
    @Test
    @Transactional
    void followRepository_test() {
        // when
        System.out.println("================= findMyFollowingUserByUserId =================");
        /**
         * inner join 쿼리 한방
         */
        List<User> joinQuery = followRepository.findMyFollowingUserByUserId(master.getId());
        System.out.println(joinQuery.get(0).getId());

        System.out.println("================= getMyFollowing =================");
        /**
         * 쿼리 안날아감
         */
        List<User> noQuery = master.getMyFollowings();
        System.out.println(noQuery.get(0).getId());

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