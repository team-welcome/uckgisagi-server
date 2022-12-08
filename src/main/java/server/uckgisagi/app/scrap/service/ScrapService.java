package server.uckgisagi.app.scrap.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.uckgisagi.app.post.service.PostServiceUtils;
import server.uckgisagi.app.user.service.UserServiceUtils;
import server.uckgisagi.app.post.domain.entity.Post;
import server.uckgisagi.app.post.domain.repository.PostRepository;
import server.uckgisagi.app.scrap.domain.entity.Scrap;
import server.uckgisagi.app.scrap.domain.repository.ScrapRepository;
import server.uckgisagi.app.user.domain.entity.User;
import server.uckgisagi.app.user.domain.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class ScrapService {

    private final ScrapRepository scrapRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public void addScrap(Long postId, Long userId) {
        User user = UserServiceUtils.findByUserId(userRepository, userId);
        Post post = PostServiceUtils.findByPostId(postRepository, postId);
        if (!scrapRepository.existsByPostAndUserId(post, userId)) {
            scrapRepository.save(Scrap.newInstance(user, post));
        }
    }

    @Transactional
    public void deleteScrap(Long postId, Long userId) {
        scrapRepository.delete(ScrapServiceUtils.findByPostIdAndUserId(scrapRepository, postId, userId));
    }
}
