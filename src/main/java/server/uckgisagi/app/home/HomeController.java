package server.uckgisagi.app.home;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import server.uckgisagi.app.home.dto.response.HomePostResponse;
import server.uckgisagi.app.home.dto.response.HomeUserResponse;
import server.uckgisagi.app.home.service.HomeRetrieveService;
import server.uckgisagi.common.dto.ApiSuccessResponse;
import server.uckgisagi.config.interceptor.Auth;
import server.uckgisagi.config.resolver.LoginUserId;
import springfox.documentation.annotations.ApiIgnore;

import static server.uckgisagi.common.success.SuccessResponseResult.*;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final HomeRetrieveService homeRetrieveService;

    @ApiOperation("[인증] 메인 홈 페이지 - 나와 친구 정보 보기")
    @Auth
    @GetMapping("/v1/home/user")
    public ApiSuccessResponse<HomeUserResponse> retrieveMeAndFriendInfo(@ApiIgnore @LoginUserId Long userId) {
        return ApiSuccessResponse.success(OK_SEARCH_MY_HOME_CONTENTS, homeRetrieveService.retrieveMeAndFriendInfo(userId));
    }

    @ApiOperation("[인증] 메인 홈 페이지 - 나의 포스트 정보 보기")
    @Auth
    @GetMapping("/v1/home/me")
    public ApiSuccessResponse<HomePostResponse> retrieveMyHomeContents(@ApiIgnore @LoginUserId Long userId) {
        return ApiSuccessResponse.success(OK_SEARCH_MY_HOME_CONTENTS, homeRetrieveService.retrieveHomeContents(userId));
    }

    @ApiOperation("[인증] 메인 홈 페이지 - 친구의 포스트 정보 보기")
    @Auth
    @GetMapping("/v1/home/{friendUserId}")
    public ApiSuccessResponse<HomePostResponse> retrieveFriendHomeContents(@PathVariable Long friendUserId) {
        return ApiSuccessResponse.success(OK_SEARCH_FRIEND_HOME_CONTENTS, homeRetrieveService.retrieveHomeContents(friendUserId));
    }

}
