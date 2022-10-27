package server.uckgisagi.domain.user.repository;

import server.uckgisagi.domain.user.entity.Token;

public interface TokenRepositoryCustom {
    String findFcmTokenByUserId(Long userId);
}
