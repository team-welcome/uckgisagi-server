package server.uckgisagi.domain.user.repository;

import server.uckgisagi.domain.user.entity.User;

import java.util.List;

public interface UserRepositoryCustom {
    User findUserByUserId(Long userId);
    List<User> findAllUserByNickname(String nickname);
}
