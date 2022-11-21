package server.uckgisagi.app.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import server.uckgisagi.app.accusation.dto.AccusePostRequestDto;
import server.uckgisagi.app.accusation.dto.AccusePostResponseDto;
import server.uckgisagi.app.image.provider.UploadProvider;
import server.uckgisagi.app.image.provider.dto.request.ImageUploadFileRequest;
import server.uckgisagi.app.post.dto.request.AddPostRequest;
import server.uckgisagi.app.post.dto.response.GradeResponse;
import server.uckgisagi.app.user.service.UserServiceUtils;
import server.uckgisagi.common.exception.custom.ConflictException;
import server.uckgisagi.common.exception.custom.NotFoundException;
import server.uckgisagi.common.type.FileType;
import server.uckgisagi.domain.accusation.entity.Accusation;
import server.uckgisagi.domain.accusation.repository.AccusationRepository;
import server.uckgisagi.domain.post.entity.Post;
import server.uckgisagi.domain.post.entity.enumerate.PostStatus;
import server.uckgisagi.domain.post.repository.PostRepository;
import server.uckgisagi.domain.user.entity.User;
import server.uckgisagi.domain.user.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static server.uckgisagi.common.exception.ErrorResponseResult.CONFLICT_EXCEPTION;
import static server.uckgisagi.common.exception.ErrorResponseResult.NOT_FOUND_POST_EXCEPTION;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final UploadProvider uploadProvider;
    private final AccusationRepository accusationRepository;

    @Transactional
    public GradeResponse addPostWithImage(AddPostRequest request, MultipartFile imageFile, Long userId) {
        String imageUrl = uploadProvider.uploadFile(ImageUploadFileRequest.from(FileType.POST_IMAGE), imageFile);
        User user = UserServiceUtils.findByUserId(userRepository, userId);
        user.addPosts(postRepository.save(request.toPostEntity(user, imageUrl)));
        return GradeResponse.from(user.getGrade());
    }

    @Transactional
    public AccusePostResponseDto accusePost(AccusePostRequestDto accusePostRequestDto, Long userId){

        Optional<Accusation> repetition = accusationRepository.findByUserIdAndPostId(userId, accusePostRequestDto.getPostId());
        if(repetition.isPresent()){
            // 해당 유저가 이미 해당 게시물을 신고한 내역이 있을 경우, 리턴 bad Request
            throw new ConflictException(String.format("이미 해당 게시글에 대한 신고 내역이 존재합니다."), CONFLICT_EXCEPTION);
        }

        User user = UserServiceUtils.findByUserId(userRepository, userId);
        Post post = PostServiceUtils.findByPostId(postRepository, accusePostRequestDto.getPostId());

        Accusation accusation = accusationRepository.save(accusePostRequestDto.toAccusationEntity(user, post));

        List<Accusation> accusations = accusationRepository.findAllByPostId(accusePostRequestDto.getPostId());
        if(accusations.size() >= 10){
            // 게시물 신고가 10번 이상일 경우 안 보이게 하는 로직
             post.changePostStatus(PostStatus.INACTIVE);
        }

        return accusation.toAccusePostResponseDto(accusation);
    }

}
