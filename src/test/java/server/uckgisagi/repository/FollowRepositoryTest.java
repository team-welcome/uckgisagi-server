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

        followRepository.saveAll(
                List.of(
                        Follow.newInstance(secondTarget, first),
                        Follow.newInstance(thirdTarget, first))
        );

        first.addFollowing(secondTarget);
        first.addFollowing(thirdTarget);

        secondTarget.addFollower(first);
        thirdTarget.addFollower(first);

        // when
        System.out.println("================= findMyFollowingUserByUserId =================");
        /**
         * inner join 쿼리 한방
         */
        followRepository.findMyFollowingUserByUserId(first.getId());

        System.out.println("================= getMyFollowing =================");
        /**
         * 쿼리 안날아감
         */
        first.getMyFollowings();

    }

}
