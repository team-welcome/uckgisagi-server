package server.uckgisagi.domain.user.repository;

import server.uckgisagi.domain.user.entity.User;

public interface UserRepositoryCustom {
    User findUserByUserId(Long userId);
}
