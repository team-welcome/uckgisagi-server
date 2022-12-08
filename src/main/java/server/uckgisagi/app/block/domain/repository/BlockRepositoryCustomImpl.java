package server.uckgisagi.app.block.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import server.uckgisagi.app.block.domain.entity.Block;
import static server.uckgisagi.app.block.domain.entity.QBlock.block;

@RequiredArgsConstructor
public class BlockRepositoryCustomImpl implements BlockRepositoryCustom{

    private final JPAQueryFactory query;

    @Override
    public Block findByBlockUserId(Long blockUserId, Long userId) {
        return query.selectFrom(block)
                .where(block.user.id.eq(userId))
                .where(block.blockUserId.eq(blockUserId))
                .fetchOne();
    }
}
