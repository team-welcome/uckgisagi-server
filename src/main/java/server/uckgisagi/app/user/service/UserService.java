package server.uckgisagi.app.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.uckgisagi.app.user.dto.request.CreateUserDto;
import server.uckgisagi.app.user.dto.request.SearchUserRequest;
import server.uckgisagi.app.user.dto.response.FollowStatus;
import server.uckgisagi.app.user.dto.response.SearchUserResponse;
import server.uckgisagi.domain.user.entity.User;
import server.uckgisagi.domain.user.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User registerUser(CreateUserDto requestDto) {
        UserServiceUtils.validateNotExistsUser(userRepository, requestDto.getSocialId(), requestDto.getSocialType());
        return userRepository.save(User.newInstance(requestDto.getSocialId(),requestDto.getSocialType(), requestDto.getNickname()));
    }

    @Transactional(readOnly = true)
    public List<SearchUserResponse> searchUserByNickname(SearchUserRequest request, Long userId) {
        User me = UserServiceUtils.findByUserId(userRepository, userId);
        List<User> myFollowings = me.getMyFollowings();

        return userRepository.findAllUserByNickname(request.getNickname()).stream()
                .map(user -> {
                    if (myFollowings.contains(user)) {
                        return SearchUserResponse.of(user, FollowStatus.ACTIVE);
                    }
                    return SearchUserResponse.of(user, FollowStatus.INACTIVE);
                }).collect(Collectors.toList());
    }

}
