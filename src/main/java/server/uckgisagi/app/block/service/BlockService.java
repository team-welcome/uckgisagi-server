package server.uckgisagi.app.block.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.uckgisagi.app.block.dto.BlockUserDto;
import server.uckgisagi.app.follow.service.FollowService;
import server.uckgisagi.domain.block.entity.Block;
import server.uckgisagi.domain.block.repository.BlockRepository;
import server.uckgisagi.domain.follow.repository.FollowRepository;
import server.uckgisagi.domain.user.entity.User;
import server.uckgisagi.domain.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class BlockService {

    private final UserRepository userRepository;
    private final FollowService followService;
    private final BlockRepository blockRepository;

    public void blockUser(BlockUserDto blockUserRequestDto, Long userId) {
        // 일단 차단하고자 하는 유저 unfollow
        User user = userRepository.findUserByUserId(userId); // 차단 버튼 누르는 유저
        User blockUser = userRepository.findUserByUserId(blockUserRequestDto.getBlockUserId()); // 차단 당하는 유저
        followService.unfollowUser(blockUser.getId(), userId);

        // Block 테이블에 데이터 추가
        blockRepository.save(Block.newInstance(user, blockUser.getId()));
    }
}
