package server.uckgisagi.domain.scrap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.uckgisagi.domain.scrap.entity.Scrap;

public interface ScrapRepository extends JpaRepository<Scrap, Long>, ScrapRepositoryCustom {
}
