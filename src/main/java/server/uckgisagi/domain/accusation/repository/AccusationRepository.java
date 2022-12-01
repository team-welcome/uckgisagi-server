package server.uckgisagi.domain.accusation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.uckgisagi.domain.accusation.entity.Accusation;

public interface AccusationRepository extends JpaRepository<Accusation, Long>, AccusationCustomRepository{
}
