package server.uckgisagi.app.block.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.uckgisagi.app.block.dto.BlockUserDto;
import server.uckgisagi.app.follow.service.FollowService;
import server.uckgisagi.domain.block.entity.Block;
import server.uckgisagi.domain.block.repository.BlockRepository;
import server.uckgisagi.domain.follow.repository.FollowRepository;
import server.uckgisagi.domain.user.entity.User;
import server.uckgisagi.domain.user.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlockService {

    private final UserRepository userRepository;
    private final FollowService followService;
    private final BlockRepository blockRepository;

    @Transactional
    public void blockUser(BlockUserDto blockUserDto, Long userId) {
        // 일단 차단하고자 하는 유저 unfollow
        User user = userRepository.findUserByUserId(userId); // 차단 버튼 누르는 유저
        User blockUser = userRepository.findUserByUserId(blockUserDto.getBlockUserId()); // 차단 당하는 유저
        followService.unfollowUser(blockUser.getId(), userId);

        // Block 테이블에 데이터 추가
        blockRepository.save(Block.newInstance(user, blockUser.getId()));
    }

    @Transactional
    public void deleteBlockUser(Long blockUserId, Long userId) {
        User blockUser = userRepository.findUserByUserId(blockUserId);
        Block block = blockRepository.findByBlockUserId(blockUser.getId(), userId);

        blockRepository.delete(block);
    }
}
