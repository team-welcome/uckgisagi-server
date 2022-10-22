package server.uckgisagi.domain.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.uckgisagi.domain.store.entity.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
