package server.uckgisagi.app.accusation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.uckgisagi.app.accusation.dto.AccusationPostReqDto;
import server.uckgisagi.app.accusation.dto.AccusationPostResDto;
import server.uckgisagi.app.post.service.PostServiceUtils;
import server.uckgisagi.app.user.service.UserServiceUtils;
import server.uckgisagi.common.exception.custom.ConflictException;
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

@Service
@RequiredArgsConstructor
public class AccusationService {

    private final AccusationRepository accusationRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;



    @Transactional
    public AccusationPostResDto accusePost(AccusationPostReqDto accusationPostReqDto, Long userId){

        Optional<Accusation> repetition = accusationRepository.findByUserIdAndPostId(userId, accusationPostReqDto.getPostId());
        if(repetition.isPresent()){
            // 해당 유저가 이미 해당 게시물을 신고한 내역이 있을 경우, 리턴 bad Request
            throw new ConflictException(String.format("이미 해당 게시글에 대한 신고 내역이 존재합니다."), CONFLICT_EXCEPTION);
        }

        User user = UserServiceUtils.findByUserId(userRepository, userId);
        Post post = PostServiceUtils.findByPostId(postRepository, accusationPostReqDto.getPostId());

        Accusation accusation = accusationRepository.save(accusationPostReqDto.toAccusationEntity(user, post));

        List<Accusation> accusations = accusationRepository.findAllByPostId(accusationPostReqDto.getPostId());
        if(accusations.size() >= 10){
            // 게시물 신고가 10번 이상일 경우 안 보이게 하는 로직
            post.changePostStatus(PostStatus.INACTIVE);
        }

        return accusation.toAccusePostResponseDto(accusation);
    }
}
