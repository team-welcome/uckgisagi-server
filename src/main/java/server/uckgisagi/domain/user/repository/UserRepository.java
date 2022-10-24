package server.uckgisagi.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.uckgisagi.domain.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
}
