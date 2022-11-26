package server.uckgisagi.app.accusation.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import server.uckgisagi.app.accusation.dto.AccusationPostReqDto;
import server.uckgisagi.app.accusation.dto.AccusationPostResDto;
import server.uckgisagi.domain.accusation.repository.AccusationRepository;
import server.uckgisagi.domain.post.entity.Post;
import server.uckgisagi.domain.post.repository.PostRepository;
import server.uckgisagi.domain.user.entity.User;
import server.uckgisagi.domain.user.entity.enumerate.SocialType;
import server.uckgisagi.domain.user.repository.UserRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class AccusationServiceTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private AccusationRepository accusationRepository;
    @Autowired
    private AccusationService accusationService;

    @DisplayName("신고 성공")
    @Test
    void accusationTest(){
        List<User> users = userRepository.saveAll(List.of(
                User.newInstance("FirstSocialId", SocialType.APPLE, "첫번째"),
                User.newInstance("SecondSocialId", SocialType.APPLE, "두번째")
        ));

        List<Post> posts = postRepository.saveAll(List.of(
                Post.newInstance(users.get(0),"", "User1의 첫 번째 게시물", "제곧네"),
                Post.newInstance(users.get(0),"", "User1의 두 번째 게시물", "제곧네"),
                Post.newInstance(users.get(1),"", "User2의 첫 번째 게시물", "제곧네"),
                Post.newInstance(users.get(1),"", "User2의 두 번째 게시물", "제곧네")
        ));

        // 두 번째 유저가 postId 1번 게시물을 신고
        AccusationPostResDto accusationPostResponseDto = accusationService.accusePost(new AccusationPostReqDto(0L),1L);
        assertEquals(accusationPostResponseDto.getPostId(), accusationRepository.findByUserIdAndPostId(0L, 1L));

    }

    @DisplayName("이미 신고 내역이 존재하는 경우 실패")
    @Test
    void accusationConflictTest(){

    }

    @DisplayName("신고 10번 이상이면 피드에 안 보이게 함.")
    @Test
    void accusationHideTest(){

    }

}