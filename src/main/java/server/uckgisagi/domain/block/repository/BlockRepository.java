package server.uckgisagi.domain.block.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.uckgisagi.domain.block.entity.Block;

@Repository
public interface BlockRepository extends JpaRepository<Block, Long> {
}
