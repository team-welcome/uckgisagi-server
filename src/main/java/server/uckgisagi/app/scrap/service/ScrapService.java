package server.uckgisagi.app.scrap.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.uckgisagi.app.post.service.PostServiceUtils;
import server.uckgisagi.app.scrap.dto.request.ScrapRequest;
import server.uckgisagi.app.user.service.UserServiceUtils;
import server.uckgisagi.domain.post.entity.Post;
import server.uckgisagi.domain.post.repository.PostRepository;
import server.uckgisagi.domain.scrap.entity.Scrap;
import server.uckgisagi.domain.scrap.repository.ScrapRepository;
import server.uckgisagi.domain.user.entity.User;
import server.uckgisagi.domain.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class ScrapService {

    private final ScrapRepository scrapRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public void addScrap(ScrapRequest request, Long userId) {
        User user = UserServiceUtils.findByUserId(userRepository, userId);
        Post post = PostServiceUtils.findByPostId(postRepository, request.getPostId());
        if (!scrapRepository.existsByPostAndUserId(post, userId)) {
            scrapRepository.save(Scrap.newInstance(user, post));
        }
    }

    @Transactional
    public void deleteScrap(Long postId, Long userId) {
        scrapRepository.delete(ScrapServiceUtils.findByPostIdAndUserId(scrapRepository, postId, userId));
    }

}
