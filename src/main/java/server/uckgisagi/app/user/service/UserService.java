package server.uckgisagi.app.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.uckgisagi.app.user.dto.request.CreateUserDto;
import server.uckgisagi.app.user.dto.response.FollowStatus;
import server.uckgisagi.app.user.dto.response.SearchUserResponse;
import server.uckgisagi.domain.follow.repository.FollowRepository;
import server.uckgisagi.domain.user.entity.Token;
import server.uckgisagi.domain.user.entity.User;
import server.uckgisagi.domain.user.repository.TokenRepository;
import server.uckgisagi.domain.user.repository.UserRepository;

import java.util.List;
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
        UserServiceUtils.findByUserId(userRepository, userId);
        List<User> myFollowings = followRepository.findMyFollowingUserByUserId(userId);
//        List<User> myFollowings = me.getMyFollowings();

        return userRepository
                .findAllUserByNickname(nickname).stream()
                .map(user -> myFollowings.contains(user)
                        ? SearchUserResponse.of(user, FollowStatus.ACTIVE)
                        : SearchUserResponse.of(user, FollowStatus.INACTIVE)
                )
                .collect(Collectors.toList());
    }

    @Transactional
    public String deleteUser(Long userId) {
        User user = userRepository.findUserByUserId(userId);
        userRepository.delete(user);

        return "회원 탈퇴 원료";
    }
}
