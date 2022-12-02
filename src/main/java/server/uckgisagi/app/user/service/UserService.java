package server.uckgisagi.app.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.uckgisagi.app.user.dto.request.CreateUserDto;
import server.uckgisagi.app.user.dto.response.FollowStatus;
import server.uckgisagi.app.user.dto.response.SearchUserResponse;
import server.uckgisagi.domain.block.entity.Block;
import server.uckgisagi.domain.follow.repository.FollowRepository;
import server.uckgisagi.domain.user.entity.Token;
import server.uckgisagi.domain.user.entity.User;
import server.uckgisagi.domain.user.repository.TokenRepository;
import server.uckgisagi.domain.user.repository.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final FollowRepository followRepository;

    @Transactional
    public User registerUser(CreateUserDto requestDto) {
        UserServiceUtils.validateNotExistsUser(userRepository, requestDto.getSocialId(), requestDto.getSocialType());
        User user = userRepository.save(User.newInstance(requestDto.getSocialId(), requestDto.getSocialType(), requestDto.getNickname()));
        user.setTokenInfo(tokenRepository.save(Token.newInstance(user.getId(), requestDto.getFcmToken())));
        return user;
    }

    @Transactional(readOnly = true)
    public List<SearchUserResponse> searchUserByNickname(String nickname, Long userId) {
        User me = UserServiceUtils.findByUserId(userRepository, userId);
        List<User> myFollowings = followRepository.findMyFollowingUserByUserId(userId);
        List<Long> blockedUserIds = me.getBlocks().stream()
                .map(Block::getBlockUserId)
                .collect(Collectors.toList());

        return userRepository
                .findAllUserByNickname(nickname, blockedUserIds).stream()
                .filter(user -> !Objects.equals(user, me)) // JPA Entity 에 equals, hashCode 오버라이딩이 옳은가?
                .map(user -> myFollowings.contains(user)
                        ? SearchUserResponse.of(user, FollowStatus.ACTIVE)
                        : SearchUserResponse.of(user, FollowStatus.INACTIVE)
                )
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteUser(Long userId) {
        userRepository.delete(UserServiceUtils.findByUserId(userRepository, userId));
    }
}
