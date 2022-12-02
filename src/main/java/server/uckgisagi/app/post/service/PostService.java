package server.uckgisagi.app.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import server.uckgisagi.app.image.provider.UploadProvider;
import server.uckgisagi.app.image.provider.dto.request.ImageUploadFileRequest;
import server.uckgisagi.app.post.dto.request.AddPostRequest;
import server.uckgisagi.app.post.dto.response.GradeResponse;
import server.uckgisagi.app.user.service.UserServiceUtils;
import server.uckgisagi.common.type.FileType;
import server.uckgisagi.app.post.domain.repository.PostRepository;
import server.uckgisagi.app.user.domain.entity.User;
import server.uckgisagi.app.user.domain.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final UploadProvider uploadProvider;

    @Transactional
    public GradeResponse addPostWithImage(AddPostRequest request, MultipartFile imageFile, Long userId) {
        String imageUrl = uploadProvider.uploadFile(ImageUploadFileRequest.from(FileType.POST_IMAGE), imageFile);
        User user = UserServiceUtils.findByUserId(userRepository, userId);
        user.addPosts(postRepository.save(request.toPostEntity(user, imageUrl)));
        return GradeResponse.from(user.getGrade());
    }

    @Transactional
    public void deletePost(Long postId, Long userId) {
        postRepository.delete(PostServiceUtils.findByPostIdAndUserId(postRepository, postId, userId));
    }
}
