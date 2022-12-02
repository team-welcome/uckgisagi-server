package server.uckgisagi.app.scrap.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.uckgisagi.app.scrap.domain.entity.Scrap;

public interface ScrapRepository extends JpaRepository<Scrap, Long>, ScrapRepositoryCustom {
}
