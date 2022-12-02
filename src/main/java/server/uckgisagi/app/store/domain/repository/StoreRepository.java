package server.uckgisagi.app.store.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.uckgisagi.app.store.domain.entity.Store;

public interface StoreRepository extends JpaRepository<Store, Long>, StoreRepositoryCustom {
}
