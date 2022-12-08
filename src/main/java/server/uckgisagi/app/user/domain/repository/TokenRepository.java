package server.uckgisagi.app.user.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.uckgisagi.app.user.domain.entity.Token;

public interface TokenRepository extends JpaRepository<Token, Long>, TokenRepositoryCustom {
}
