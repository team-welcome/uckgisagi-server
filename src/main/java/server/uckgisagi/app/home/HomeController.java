package server.uckgisagi.app.home;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import server.uckgisagi.app.home.dto.response.HomeResponse;
import server.uckgisagi.common.dto.ApiSuccessResponse;

import static server.uckgisagi.common.success.SuccessResponseResult.*;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final HomeRetrieveService homeRetrieveService;

    @GetMapping("/v1/home")
    public ApiSuccessResponse<HomeResponse> retrieveMyHomeContents(Long userId) {
        return ApiSuccessResponse.success(OK_SEARCH_MY_HOME_CONTENTS, homeRetrieveService.retrieveMyHomeContents(userId));
    }

    @GetMapping("/v1/home/{friendUserId}")
    public ApiSuccessResponse<HomeResponse> retrieveFriendHomeContents(@PathVariable Long friendUserId, Long userId) {
        return ApiSuccessResponse.success(OK_SEARCH_FRIEND_HOME_CONTENTS, homeRetrieveService.retrieveFriendHomeContents(userId, friendUserId));
    }

}
