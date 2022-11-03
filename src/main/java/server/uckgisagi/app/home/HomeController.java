package server.uckgisagi.app.home;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import server.uckgisagi.app.home.dto.response.HomeResponse;
import server.uckgisagi.common.dto.ApiSuccessResponse;
import server.uckgisagi.config.interceptor.Auth;
import server.uckgisagi.config.resolver.LoginUserId;
import springfox.documentation.annotations.ApiIgnore;

import static server.uckgisagi.common.success.SuccessResponseResult.*;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final HomeRetrieveService homeRetrieveService;

    @ApiOperation("[인증] 메인 홈 페이지 - 나의 홈 페이지 보기")
    @Auth
    @GetMapping("/v1/home")
    public ApiSuccessResponse<HomeResponse> retrieveMyHomeContents(@ApiIgnore @LoginUserId Long userId) {
        return ApiSuccessResponse.success(OK_SEARCH_MY_HOME_CONTENTS, homeRetrieveService.retrieveMyHomeContents(userId));
    }

    @ApiOperation("[인증] 메인 홈 페이지 - 친구의 홈 페이지 보기")
    @Auth
    @GetMapping("/v1/home/{friendUserId}")
    public ApiSuccessResponse<HomeResponse> retrieveFriendHomeContents(@PathVariable Long friendUserId,  @ApiIgnore @LoginUserId Long userId) {
        return ApiSuccessResponse.success(OK_SEARCH_FRIEND_HOME_CONTENTS, homeRetrieveService.retrieveFriendHomeContents(userId, friendUserId));
    }

}
