package server.uckgisagi.app.scrap.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import server.uckgisagi.common.exception.custom.NotFoundException;
import server.uckgisagi.domain.post.entity.Post;
import server.uckgisagi.domain.post.repository.PostRepository;
import server.uckgisagi.domain.scrap.entity.Scrap;
import server.uckgisagi.domain.scrap.repository.ScrapRepository;
import server.uckgisagi.domain.user.entity.User;
import server.uckgisagi.domain.user.entity.enumerate.SocialType;
import server.uckgisagi.domain.user.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = {"spring.config.location=classpath:application-test.yml"})
public class ScrapServiceTest {

    @Autowired
    private ScrapService scrapService;

    @Autowired
    private ScrapRepository scrapRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    private Post post;
    private User user;

    @BeforeEach
    void setup() {
        user = User.newInstance("SOCIAL_ID", SocialType.APPLE, "NICKNAME");
        post = Post.newInstance(user, "imageURL", "Title", "Content");

        userRepository.save(user);
        postRepository.save(post);
    }

    @AfterEach
    void clean() {
        scrapRepository.deleteAllInBatch();
        postRepository.deleteAllInBatch();
        userRepository.deleteAllInBatch();
    }

    @DisplayName("스크랩 성공")
    @Test
    @Transactional
    void add_scrap_success() {
        // given
        final Long USER_ID = user.getId();
        final Long POST_ID = post.getId();

        // when
        scrapService.addScrap(POST_ID, USER_ID);

        // then
        Scrap scrap = scrapRepository.findScrapByPostIdAndUserId(POST_ID, USER_ID);
        assertThat(scrap).isNotNull();
    }

    @DisplayName("스크랩 취소 성공")
    @Test
    @Transactional
    void delete_scrap_success() {
        // given
        Scrap scrap = Scrap.newInstance(user, post);
        scrapRepository.save(scrap);

        // when
        scrapService.deleteScrap(post.getId(), user.getId());

        // then
        boolean isDeleteScrap = scrapRepository.findById(scrap.getId()).isEmpty();
        assertTrue(isDeleteScrap);
    }

    @DisplayName("존재하지 않는 게시물 스크랩 시도 시 실패")
    @Test
    void throw_NotFoundException_when_request_not_exist_post() {
        final Long NOT_EXIST_POST_ID = - 1L;
        assertThrows(NotFoundException.class, () -> scrapService.addScrap(NOT_EXIST_POST_ID, user.getId()));
    }

    @DisplayName("스크랩하지 않은 게시물 스크랩 시도 시 실패")
    @Test
    void throw_NotFoundException_when_request_not_exist_scrap() {
        assertThrows(NotFoundException.class, () -> scrapService.deleteScrap(post.getId(), user.getId()));
    }
}
