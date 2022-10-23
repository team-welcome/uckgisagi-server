package server.uckgisagi.domain.store.repository;

import server.uckgisagi.domain.store.entity.Store;

import java.util.List;

public interface StoreRepositoryCustom {
    List<Store> findAllStore();
    Store findStoreByStoreId(Long storeId);
}
