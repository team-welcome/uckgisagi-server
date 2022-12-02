package server.uckgisagi.app.store.domain.repository;

import server.uckgisagi.app.store.domain.entity.Store;

import java.util.List;

public interface StoreRepositoryCustom {
    List<Store> findAllStore();
    Store findStoreByStoreId(Long storeId);
}
