package server.uckgisagi.app.store;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import server.uckgisagi.app.store.dto.response.AllStoreResponse;
import server.uckgisagi.app.store.dto.response.OneStoreResponse;
import server.uckgisagi.app.store.service.StoreRetrieveService;
import server.uckgisagi.common.dto.ApiSuccessResponse;

import static server.uckgisagi.common.success.SuccessResponseResult.*;

@RestController
@RequiredArgsConstructor
public class StoreController {

    private final StoreRetrieveService storeRetrieveService;

    @GetMapping("/v1/store")
    public ApiSuccessResponse<AllStoreResponse> retrieveAllStore() {
        return ApiSuccessResponse.success(OK_RETRIEVE_ALL_STORE, storeRetrieveService.retrieveAllStore());
    }

    @GetMapping("/v1/store/{storeId}")
    public ApiSuccessResponse<OneStoreResponse> retrieveOneStore(@PathVariable Long storeId) {
        return ApiSuccessResponse.success(OK_RETRIEVE_ONE_STORE, storeRetrieveService.retrieveOneStore(storeId));
    }

}
