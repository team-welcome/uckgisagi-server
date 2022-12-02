package server.uckgisagi.app.block.domain.repository;

import server.uckgisagi.app.block.domain.entity.Block;

public interface BlockRepositoryCustom {
    Block findByBlockUserId(Long blockUserId, Long userId);
}
