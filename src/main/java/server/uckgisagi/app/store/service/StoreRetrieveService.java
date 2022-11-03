package server.uckgisagi.app.store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.uckgisagi.app.store.dto.response.AllStoreResponse;
import server.uckgisagi.app.store.dto.response.OneStoreResponse;
import server.uckgisagi.app.store.dto.response.PreviewStoreDto;
import server.uckgisagi.domain.store.entity.Store;
import server.uckgisagi.domain.store.repository.StoreRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreRetrieveService {

    private final StoreRepository storeRepository;

    private static final long LIMIT_SIZE = 5L;

    public AllStoreResponse retrieveAllStore() {
        List<Store> allStore = storeRepository.findAllStore();

        List<PreviewStoreDto> popularStoreResponseDto = allStore.stream().limit(LIMIT_SIZE)
                .map(PreviewStoreDto::from)
                .collect(Collectors.toList());
        List<PreviewStoreDto> restStoreResponseDto = allStore.stream().skip(LIMIT_SIZE)
                .map(PreviewStoreDto::from)
                .collect(Collectors.toList());

        return AllStoreResponse.of(popularStoreResponseDto, restStoreResponseDto);
    }

    public OneStoreResponse retrieveOneStore(Long storeId) {
        return OneStoreResponse.from(StoreServiceUtils.findByStoreId(storeRepository, storeId));
    }

}
