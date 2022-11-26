package server.uckgisagi.app.user;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import server.uckgisagi.app.user.dto.response.SearchUserResponse;
import server.uckgisagi.app.user.service.UserService;
import server.uckgisagi.common.dto.ApiSuccessResponse;
import server.uckgisagi.config.interceptor.Auth;
import server.uckgisagi.config.resolver.LoginUserId;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

import static server.uckgisagi.common.success.SuccessResponseResult.OK_SEARCH_USER;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiOperation("[인증] 유저 검색 페이지 - 유저 닉네임으로 찾기")
    @Auth
    @GetMapping("/v1/user/search")
    public ApiSuccessResponse<List<SearchUserResponse>> searchUserByNickname(@RequestParam String nickname, @ApiIgnore @LoginUserId Long userId) {
        return ApiSuccessResponse.success(OK_SEARCH_USER, userService.searchUserByNickname(nickname, userId));
    }

    @ApiOperation("[인증] 유저 차단 페이지 - 특정 유저 차단하기")
    @Auth
    @GetMapping("/v1/user/search")
    public ApiSuccessResponse<List<SearchUserResponse>> blockUser(@RequestParam String nickname, @ApiIgnore @LoginUserId Long userId) {
        return ApiSuccessResponse.success(OK_SEARCH_USER, userService.searchUserByNickname(nickname, userId));
    }

}
