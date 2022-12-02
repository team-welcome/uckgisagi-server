package server.uckgisagi.app.user.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.uckgisagi.app.user.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
}
