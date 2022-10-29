package server.uckgisagi.app.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.uckgisagi.app.post.dto.response.DetailPostResponse;
import server.uckgisagi.app.post.dto.response.PreviewPostResponse;
import server.uckgisagi.app.post.dto.response.ScrapStatus;
import server.uckgisagi.app.user.service.UserServiceUtils;
import server.uckgisagi.domain.post.entity.Post;
import server.uckgisagi.domain.post.repository.PostRepository;
import server.uckgisagi.domain.scrap.repository.ScrapRepository;
import server.uckgisagi.domain.user.entity.User;
import server.uckgisagi.domain.user.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostRetrieveService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ScrapRepository scrapRepository;

    public List<PreviewPostResponse> retrieveAllPost(Long userId) {
        List<Post> scrapedPosts = scrapRepository.findScrapPostByUserId(userId);
        return postRepository
                .findAll(Sort.by(Sort.Direction.DESC, "createdAt")).stream()
                .map(post -> scrapedPosts.contains(post)
                        ? PreviewPostResponse.of(post, ScrapStatus.ACTIVE)
                        : PreviewPostResponse.of(post, ScrapStatus.INACTIVE))
                .collect(Collectors.toList());
    }

    public List<PreviewPostResponse> retrieveScrapPost(Long userId) {
        return scrapRepository.findScrapPostByUserId(userId).stream()
                .map(post -> PreviewPostResponse.of(post, ScrapStatus.ACTIVE))
                .collect(Collectors.toList());
    }

    public DetailPostResponse retrieveDetailPost(Long postId, Long userId) {
        User user = UserServiceUtils.findByUserId(userRepository, userId);
        Post post = PostServiceUtils.findByPostId(postRepository, postId);
        return scrapRepository.existsByPostAndUserId(post, userId)
                ? DetailPostResponse.of(post, user.getNickname(), ScrapStatus.ACTIVE)
                : DetailPostResponse.of(post, user.getNickname(), ScrapStatus.INACTIVE);
    }

    public DetailPostResponse retrieveDetailScrapPost(Long postId, Long userId) {
        return DetailPostResponse.of(
                PostServiceUtils.findByPostId(postRepository, postId),
                UserServiceUtils.findByUserId(userRepository, userId).getNickname(),
                ScrapStatus.ACTIVE
        );
    }

}