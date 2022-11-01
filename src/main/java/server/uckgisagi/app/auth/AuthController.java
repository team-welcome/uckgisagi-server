package server.uckgisagi.app.auth;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import server.uckgisagi.app.auth.dto.request.LoginRequest;
import server.uckgisagi.app.auth.dto.request.TokenRequest;
import server.uckgisagi.app.auth.dto.response.LoginResponse;
import server.uckgisagi.app.auth.dto.response.TokenResponse;
import server.uckgisagi.app.auth.provider.AuthServiceProvider;
import server.uckgisagi.app.auth.service.AuthService;
import server.uckgisagi.app.auth.service.CreateTokenService;
import server.uckgisagi.common.dto.ApiSuccessResponse;
import server.uckgisagi.domain.user.entity.User;

import javax.validation.Valid;

import static server.uckgisagi.common.success.SuccessResponseResult.CREATED_REISSUE_TOKEN;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceProvider authServiceProvider;
    private final CreateTokenService createTokenService;

    @ApiOperation("회원가입 및 로그인 페이지 - 회원가입 및 로그인")
    @PostMapping("/v1/auth/login")
    public ApiSuccessResponse<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        AuthService authService = authServiceProvider.getAuthService(request.getSocialType());
        User user = authService.login(request.toServiceDto());
        TokenResponse tokenInfo = createTokenService.createTokenInfo(user.getId());
        return ApiSuccessResponse.success(LoginResponse.of(user, tokenInfo));
    }

    @ApiOperation("토큰 만료 시 엑세스 토큰 재발급 요청")
    @PostMapping("/v1/auth/reissue")
    public ApiSuccessResponse<TokenResponse> reissueToken(@Valid @RequestBody TokenRequest request) {
        return ApiSuccessResponse.success(CREATED_REISSUE_TOKEN, createTokenService.reissueToken(request));
    }

}
