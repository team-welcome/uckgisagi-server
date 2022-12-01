package server.uckgisagi.domain.block.repository;

import server.uckgisagi.domain.block.entity.Block;

public interface BlockRepositoryCustom {
    Block findByBlockUserId(Long blockUserId, Long userId);
}
