package server.uckgisagi.app.user.domain.repository;

public interface TokenRepositoryCustom {
    String findFcmTokenByUserId(Long userId);
}
