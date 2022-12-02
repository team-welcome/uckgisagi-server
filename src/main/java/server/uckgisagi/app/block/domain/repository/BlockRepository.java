package server.uckgisagi.app.block.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.uckgisagi.app.block.domain.entity.Block;

@Repository
public interface BlockRepository extends JpaRepository<Block, Long>, BlockRepositoryCustom {
}
