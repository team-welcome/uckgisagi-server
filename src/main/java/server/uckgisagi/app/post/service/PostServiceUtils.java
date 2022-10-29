package server.uckgisagi.app.post.service;

import org.jetbrains.annotations.NotNull;
import server.uckgisagi.common.exception.custom.NotFoundException;
import server.uckgisagi.domain.post.entity.Post;
import server.uckgisagi.domain.post.repository.PostRepository;

import static server.uckgisagi.common.exception.ErrorResponseResult.*;

public class PostServiceUtils {

    @NotNull
    public static Post findByPostId(PostRepository postRepository, Long postId) {
        Post post = postRepository.findPostByPostId(postId);
        if (post == null) {
            throw new NotFoundException(String.format("존재하지 않는 게시글 (%s) 입니다", postId), NOT_FOUND_POST_EXCEPTION);
        }
        return post;
    }

}
