package server.uckgisagi.app.user.service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import server.uckgisagi.common.exception.custom.NotFoundException;
import server.uckgisagi.domain.user.entity.User;
import server.uckgisagi.domain.user.repository.UserRepository;

import static server.uckgisagi.common.exception.ErrorResponseResult.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserServiceUtils {

    @NotNull
    public static User findByUserId(UserRepository userRepository, Long userId) {
        User user = userRepository.findUserByUserId(userId);
        if (user == null) {
            throw new NotFoundException(String.format("존재하지 않는 유저 (%s) 입니다", userId), NOT_FOUND_USER_EXCEPTION);
        }
        return user;
    }

}
