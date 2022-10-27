package server.uckgisagi.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.uckgisagi.domain.user.entity.Token;

public interface TokenRepository extends JpaRepository<Token, Long>, TokenRepositoryCustom {
}
