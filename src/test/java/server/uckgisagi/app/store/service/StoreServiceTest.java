package server.uckgisagi.app.store.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import server.uckgisagi.app.store.dto.response.AllStoreResponse;
import server.uckgisagi.app.store.dto.response.OneStoreResponse;
import server.uckgisagi.common.exception.custom.NotFoundException;
import server.uckgisagi.domain.store.entity.Store;
import server.uckgisagi.domain.store.repository.StoreRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = {"spring.config.location=classpath:application-test.yml"})
public class StoreServiceTest {

    @Autowired
    private StoreRetrieveService storeRetrieveService;

    @Autowired
    private StoreRepository storeRepository;

    @DisplayName("모든 리필스테이션 정보 조회 성공 (상단 매장 정보가 5개)")
    @Test
    void all_store_info_retrieve_success() {
        final int TOP_STORE_COUNT = 5;
        // when
        AllStoreResponse response = storeRetrieveService.retrieveAllStore();

        // then
        assertAll(
                () -> assertThat(response).isNotNull(),
                () -> assertEquals(response.getMostPopularStore().size(), TOP_STORE_COUNT)
        );
    }

    @DisplayName("리필스테이션 상세 정보 조회 성공")
    @Test
    @Transactional(readOnly = true)
    void one_store_info_retrieve_success() {
        // given
        final Long STORE_ID = 1L;
        Store store = storeRepository.findStoreByStoreId(STORE_ID);

        // when
        OneStoreResponse response = storeRetrieveService.retrieveOneStore(STORE_ID);

        // then
        assertAll(
                () -> assertThat(store).isNotNull(),
                () -> assertThat(response).isNotNull(),
                () -> assertEquals(response.getStoreId(), store.getId()),
                () -> assertEquals(response.getStoreName(), store.getName()),
                () -> assertEquals(response.getDescription(), store.getDescription()),
                () -> assertEquals(response.getAddress(), store.getAddress()),
                () -> assertEquals(response.getImageUrl(), store.getImageUrl()),
                () -> assertEquals(response.getWebSite(), store.getWebSite()),
                () -> assertEquals(response.getPhoneNumber(), store.getPhoneNumber()),
                () -> assertEquals(response.getTags(), store.getStoreTagValue())
        );
    }

    @DisplayName("존재하지 않는 매장 정보 조회 시 실패")
    @Test
    void throw_NotFoundException_when_request_not_exist_store() {
        final Long NOT_EXIST_STORE_ID = - 1L;
        assertThrows(NotFoundException.class, () -> storeRetrieveService.retrieveOneStore(NOT_EXIST_STORE_ID));
    }
}
