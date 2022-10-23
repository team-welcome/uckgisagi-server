package server.uckgisagi.domain.store.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import server.uckgisagi.domain.store.entity.Store;

import java.util.List;

import static server.uckgisagi.domain.store.entity.QStore.*;

@RequiredArgsConstructor
public class StoreRepositoryCustomImpl implements StoreRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public List<Store> findAllStore() {
        return query
                .selectFrom(store)
                .orderBy(store.reviews.size().desc())
                .fetch();
    }

    @Override
    public Store findStoreByStoreId(Long storeId) {
        return query
                .selectFrom(store)
                .where(store.id.eq(storeId))
                .fetchOne();
    }

}
