package server.uckgisagi.app.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.uckgisagi.app.post.dto.response.DetailPostResponse;
import server.uckgisagi.app.post.dto.response.PreviewPostResponse;
import server.uckgisagi.app.post.dto.response.ScrapStatus;
import server.uckgisagi.app.user.service.UserServiceUtils;
import server.uckgisagi.app.block.domain.entity.Block;
import server.uckgisagi.app.post.domain.entity.Post;
import server.uckgisagi.app.post.domain.repository.PostRepository;
import server.uckgisagi.app.scrap.domain.repository.ScrapRepository;
import server.uckgisagi.app.user.domain.entity.User;
import server.uckgisagi.app.user.domain.repository.UserRepository;

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
        User me = userRepository.findUserByUserId(userId);
        List<Long> blockUserIds = me.getBlocks().stream()
                .map(Block::getBlockUserId)
                .collect(Collectors.toList());
        List<Post> scrapedPosts = scrapRepository.findScrapPostByUserId(userId, blockUserIds);

        return postRepository
                .findAllByPostStatus(blockUserIds).stream()
                .map(post -> scrapedPosts.contains(post)
                        ? PreviewPostResponse.of(post, ScrapStatus.ACTIVE)
                        : PreviewPostResponse.of(post, ScrapStatus.INACTIVE))
                .collect(Collectors.toList());
    }

    public List<PreviewPostResponse> retrieveScrapPost(Long userId) {
        List<Long> blockUserIds = userRepository.findUserByUserId(userId)
                .getBlocks().stream()
                .map(Block::getBlockUserId)
                .collect(Collectors.toList());

        return scrapRepository.findScrapPostByUserId(userId, blockUserIds).stream()
                .map(post -> PreviewPostResponse.of(post, ScrapStatus.ACTIVE))
                .collect(Collectors.toList());
    }

    public DetailPostResponse retrieveDetailPost(Long postId, Long userId) {
//        UserServiceUtils.validateExistUser(userRepository, userId); // TODO ?????? ?????? api ?????? ?????? ????????? ????????????????
        Post post = PostServiceUtils.findByPostId(postRepository, postId);
        return scrapRepository.existsByPostAndUserId(post, userId)
                ? DetailPostResponse.of(post, ScrapStatus.ACTIVE)
                : DetailPostResponse.of(post, ScrapStatus.INACTIVE);
    }

    public DetailPostResponse retrieveDetailScrapPost(Long postId, Long userId) {
//        UserServiceUtils.validateExistUser(userRepository, userId);
        return DetailPostResponse.of(
                PostServiceUtils.findByPostId(postRepository, postId),
                ScrapStatus.ACTIVE
        );
    }
}
