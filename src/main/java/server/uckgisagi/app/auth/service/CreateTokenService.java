package server.uckgisagi.app.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.uckgisagi.app.auth.dto.request.TokenRequest;
import server.uckgisagi.app.auth.dto.response.TokenResponse;
import server.uckgisagi.common.exception.custom.UnAuthorizedException;
import server.uckgisagi.common.util.JwtUtils;

@Service
@RequiredArgsConstructor
public class CreateTokenService {

    private final JwtUtils jwtProvider;

    @Transactional
    public TokenResponse createTokenInfo(Long userId) {
        return jwtProvider.createTokenByUserId(userId);
    }

    @Transactional
    public TokenResponse reissueToken(TokenRequest request) {
        if (!jwtProvider.validateToken(request.getRefreshToken())) {
            throw new UnAuthorizedException(String.format("리프레시 토큰 (%s) 이 유효하지 않습니다", request.getRefreshToken()));
        }
        Long userId = jwtProvider.getUserIdFromJwt(request.getAccessToken());
        return jwtProvider.createTokenByUserId(userId);
    }

}
