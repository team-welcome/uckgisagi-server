package server.uckgisagi.app.store.service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import server.uckgisagi.common.exception.custom.NotFoundException;
import server.uckgisagi.domain.store.entity.Store;
import server.uckgisagi.domain.store.repository.StoreRepository;

import static server.uckgisagi.common.exception.ErrorResponseResult.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StoreServiceUtils {

    public static void validateNotExistsStore(StoreRepository storeRepository, Long storeId) {
        if (!storeRepository.existsById(storeId)) {
            throw new NotFoundException(String.format("해당하는 리필 스테이션 (%s) 이 존재하지 않습니다", storeId), NOT_FOUND_STORE_EXCEPTION);
        }
    }

    @NotNull
    public static Store findByStoreId(StoreRepository storeRepository, Long storeId) {
        Store store = storeRepository.findStoreByStoreId(storeId);
        if (store == null) {
            throw new NotFoundException(String.format("해당하는 리필 스테이션 (%s) 이 존재하지 않습니다", storeId), NOT_FOUND_STORE_EXCEPTION);
        }
        return store;
    }

}
