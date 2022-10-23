package server.uckgisagi.app.store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.uckgisagi.domain.store.repository.StoreRepository;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

}
