package server.uckgisagi.app.home.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import server.uckgisagi.app.home.dto.response.HomePostResponse;
import server.uckgisagi.app.home.dto.response.HomeUserResponse;
import server.uckgisagi.app.home.dto.response.TodayPostStatus;
import server.uckgisagi.app.home.dto.response.UserResponseDto;
import server.uckgisagi.app.post.dto.response.PostResponse;
import server.uckgisagi.domain.follow.entity.Follow;
import server.uckgisagi.domain.follow.repository.FollowRepository;
import server.uckgisagi.domain.post.entity.Post;
import server.uckgisagi.domain.post.repository.PostRepository;
import server.uckgisagi.domain.user.entity.User;
import server.uckgisagi.domain.user.entity.enumerate.SocialType;
import server.uckgisagi.domain.user.repository.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(properties = {"spring.config.location=classpath:application-test.yml"})
@Transactional
public class HomeRetrieveServiceTest {

    @Autowired
    private HomeRetrieveService homeRetrieveService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private FollowRepository followRepository;

    private User master;
    private User friend;

    @BeforeEach
    void setup() {
        // given
        master = User.newInstance("SOCIAL_ID", SocialType.APPLE, "MasterUser");
        friend = User.newInstance("SOCIAL_ID", SocialType.APPLE, "Friend_1");

        userRepository.saveAll(List.of(master, friend));
        followRepository.save(Follow.newInstance(friend, master));
    }

    @AfterEach
    void clean() {
        postRepository.deleteAllInBatch();
        followRepository.deleteAllInBatch();
        userRepository.deleteAllInBatch();
    }

    @DisplayName("상단 바 - 나와 내 친구 모두 오늘 올린 인증글이 없을 경우 정보 조회 성공")
    @Test
    void retrieve_me_and_friend_info_success_no_post_today() {
        // when
        HomeUserResponse response = homeRetrieveService.retrieveMeAndFriendInfo(master.getId());

        // then
        UserResponseDto myInfo = response.getMyInfo();
        UserResponseDto friendInfo = response.getFriendInfo().get(ONLY_FRIEND);
        assertAll(
                () -> assertThat(response).isNotNull(),
                () -> assertThat(myInfo).isNotNull(),
                () -> assertThat(response.getFriendInfo()).isNotEmpty(),
                () -> assertEquals(myInfo.getUserId(), master.getId()),
                () -> assertEquals(myInfo.getNickname(), master.getNickname()),
                () -> assertEquals(myInfo.getGrade(), master.getGrade()),
                () -> assertEquals(myInfo.getPostStatus(), TodayPostStatus.INACTIVE),
                () -> assertEquals(friendInfo.getUserId(), friend.getId()),
                () -> assertEquals(friendInfo.getNickname(), friend.getNickname()),
                () -> assertEquals(friendInfo.getGrade(), friend.getGrade()),
                () -> assertEquals(friendInfo.getPostStatus(), TodayPostStatus.INACTIVE)
        );
    }

    @DisplayName("상단 바 - 나만 오늘 올린 인증글이 있는 경우 정보 조회 성공")
    @Test
    void retrieve_me_and_friend_info_success_only_me_post_today() {
        // given
        postRepository.save(Post.newInstance(master, "imageUrl", "Title", "Content"));

        // when
        HomeUserResponse response = homeRetrieveService.retrieveMeAndFriendInfo(master.getId());

        // then
        UserResponseDto myInfo = response.getMyInfo();
        UserResponseDto friendInfo = response.getFriendInfo().get(ONLY_FRIEND);
        assertAll(
                () -> assertThat(response).isNotNull(),
                () -> assertThat(myInfo).isNotNull(),
                () -> assertThat(response.getFriendInfo()).isNotEmpty(),
                () -> assertEquals(myInfo.getUserId(), master.getId()),
                () -> assertEquals(myInfo.getNickname(), master.getNickname()),
                () -> assertEquals(myInfo.getGrade(), master.getGrade()),
                () -> assertEquals(myInfo.getPostStatus(), TodayPostStatus.ACTIVE),
                () -> assertEquals(friendInfo.getUserId(), friend.getId()),
                () -> assertEquals(friendInfo.getNickname(), friend.getNickname()),
                () -> assertEquals(friendInfo.getGrade(), friend.getGrade()),
                () -> assertEquals(friendInfo.getPostStatus(), TodayPostStatus.INACTIVE)
        );
    }

    @DisplayName("상단 바 - 친구만 오늘 올린 인증글이 있는 경우 정보 조회 성공")
    @Test
    void retrieve_me_and_friend_info_success_only_friend_post_today() {
        // given
        postRepository.save(Post.newInstance(friend, "imageUrl", "Title", "Content"));

        // when
        HomeUserResponse response = homeRetrieveService.retrieveMeAndFriendInfo(master.getId());

        // then
        UserResponseDto myInfo = response.getMyInfo();
        UserResponseDto friendInfo = response.getFriendInfo().get(ONLY_FRIEND);
        assertAll(
                () -> assertThat(response).isNotNull(),
                () -> assertThat(myInfo).isNotNull(),
                () -> assertThat(response.getFriendInfo()).isNotEmpty(),
                () -> assertEquals(myInfo.getUserId(), master.getId()),
                () -> assertEquals(myInfo.getNickname(), master.getNickname()),
                () -> assertEquals(myInfo.getGrade(), master.getGrade()),
                () -> assertEquals(myInfo.getPostStatus(), TodayPostStatus.INACTIVE),
                () -> assertEquals(friendInfo.getUserId(), friend.getId()),
                () -> assertEquals(friendInfo.getNickname(), friend.getNickname()),
                () -> assertEquals(friendInfo.getGrade(), friend.getGrade()),
                () -> assertEquals(friendInfo.getPostStatus(), TodayPostStatus.ACTIVE)
        );
    }

    @DisplayName("상단 바 - 나와 친구 모두 오늘 올린 인증글이 있는 경우 정보 조회 성공")
    @Test
    void retrieve_me_and_friend_info_success_me_and_friend_post_today() {
        // given
        postRepository.save(Post.newInstance(master, "imageUrl", "Title", "Content"));
        postRepository.save(Post.newInstance(friend, "imageUrl", "Title", "Content"));

        // when
        HomeUserResponse response = homeRetrieveService.retrieveMeAndFriendInfo(master.getId());

        // then
        UserResponseDto myInfo = response.getMyInfo();
        UserResponseDto friendInfo = response.getFriendInfo().get(ONLY_FRIEND);
        assertAll(
                () -> assertThat(response).isNotNull(),
                () -> assertThat(myInfo).isNotNull(),
                () -> assertThat(response.getFriendInfo()).isNotEmpty(),
                () -> assertEquals(myInfo.getUserId(), master.getId()),
                () -> assertEquals(myInfo.getNickname(), master.getNickname()),
                () -> assertEquals(myInfo.getGrade(), master.getGrade()),
                () -> assertEquals(myInfo.getPostStatus(), TodayPostStatus.ACTIVE),
                () -> assertEquals(friendInfo.getUserId(), friend.getId()),
                () -> assertEquals(friendInfo.getNickname(), friend.getNickname()),
                () -> assertEquals(friendInfo.getGrade(), friend.getGrade()),
                () -> assertEquals(friendInfo.getPostStatus(), TodayPostStatus.ACTIVE)
        );
    }

    @DisplayName("나의 홈 컨텐츠 조회 성공 - 오늘 올린 인증글이 없는 경우")
    @Test
    void retrieve_my_home_contents_success_not_exist_today_post() {
        // given

        // when

        // then
    }

    @DisplayName("나의 홈 컨텐츠 조회 성공 - 오늘 올린 인증글이 있는 경우")
    @Test
    void retrieve_my_home_contents_success_exist_today_post() {
        // given
        Post oldPost1 = Post.newInstance(master, "imageUrl", "Title", "Content");
        Post oldPost2 = Post.newInstance(master, "imageUrl", "Title", "Content");
        Post oldPost3 = Post.newInstance(master, "imageUrl", "Title", "Content");

        oldPost1.setTestCreatedAt(LocalDateTime.of(LocalDate.of(TODAY_DATE.getYear(), TODAY_DATE.getMonthValue(), 2), LOCAL_TIME));
        oldPost2.setTestCreatedAt(LocalDateTime.of(LocalDate.of(TODAY_DATE.getYear(), TODAY_DATE.getMonthValue(), 10), LOCAL_TIME));
        oldPost3.setTestCreatedAt(LocalDateTime.of(LocalDate.of(TODAY_DATE.getYear(), TODAY_DATE.getMonthValue(), 15), LOCAL_TIME));

        Post todayPost = Post.newInstance(master, "imageUrl", "Title", "Content");

        postRepository.saveAll(List.of(oldPost1, oldPost2, oldPost3, todayPost));

        // when
        HomePostResponse response = homeRetrieveService.retrieveHomeContents(master.getId());

        // then

        List<LocalDate> postDates = response.getPostDates();
        List<PostResponse> postResponses = response.getPosts();
//        assertAll(
//                () -> assertThat(response).isNotNull(),
//                () -> assertThat(postDates).isNotEmpty(),
//                () -> assertThat(postResponses).isNotEmpty()
//        );
    }

    private final LocalDate TODAY_DATE = LocalDate.now(ZoneId.of("Asia/Seoul"));

    private static final LocalTime LOCAL_TIME = LocalTime.of(0, 0);
    private static final int ONLY_FRIEND = 0;
}
