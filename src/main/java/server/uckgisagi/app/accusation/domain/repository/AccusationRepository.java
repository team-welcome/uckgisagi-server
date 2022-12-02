package server.uckgisagi.app.accusation.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.uckgisagi.app.accusation.domain.entity.Accusation;

public interface AccusationRepository extends JpaRepository<Accusation, Long>, AccusationCustomRepository{
}
