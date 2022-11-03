package server.uckgisagi.app.store;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import server.uckgisagi.app.store.dto.response.AllStoreResponse;
import server.uckgisagi.app.store.dto.response.OneStoreResponse;
import server.uckgisagi.app.store.service.StoreRetrieveService;
import server.uckgisagi.common.dto.ApiSuccessResponse;
import server.uckgisagi.config.interceptor.Auth;

import static server.uckgisagi.common.success.SuccessResponseResult.*;

@RestController
@RequiredArgsConstructor
public class StoreController {

    private final StoreRetrieveService storeRetrieveService;

    @ApiOperation("[인증] 실천장소 보기 페이지 - 모든 리필스테이션 정보 조회")
    @Auth
    @GetMapping("/v1/store")
    public ApiSuccessResponse<AllStoreResponse> retrieveAllStore() {
        return ApiSuccessResponse.success(OK_RETRIEVE_ALL_STORE, storeRetrieveService.retrieveAllStore());
    }

    @ApiOperation("[인증] 실천장소 보기 페이지에서 매장 정보 클릭 시 - 리필스테이션 상세 정보 조회")
    @Auth
    @GetMapping("/v1/store/{storeId}")
    public ApiSuccessResponse<OneStoreResponse> retrieveOneStore(@PathVariable Long storeId) {
        return ApiSuccessResponse.success(OK_RETRIEVE_ONE_STORE, storeRetrieveService.retrieveOneStore(storeId));
    }

}
