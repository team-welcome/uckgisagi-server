package server.uckgisagi.common.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import server.uckgisagi.app.auth.dto.response.TokenResponse;
import server.uckgisagi.config.security.JwtConstants;

import java.security.Key;
import java.util.Date;

@Slf4j
@Component
@PropertySource(value = "classpath:application-jwt.yml", factory = YamlPropertySourceFactory.class, ignoreResourceNotFound = true)
public class JwtUtils {

    private static final long ACCESS_TOKEN_EXPIRES_TIME = 14 * 24 * 60 * 60 * 1000L;   // 30분 -> 개발시 14일
    private static final long REFRESH_TOKEN_EXPIRES_TIME = 30 * 24 * 60 * 60 * 1000L;  // 7일  -> 개발시 30일

    private final Key secretKey;

    public JwtUtils(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public TokenResponse createTokenByUserId(Long userId) {
        long now = (new Date()).getTime();
        Date accessTokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRES_TIME);
        Date refreshTokenExpiresIn = new Date(now + REFRESH_TOKEN_EXPIRES_TIME);

        // accessToken 생성
        String accessToken = Jwts.builder()
                .claim(JwtConstants.USER_ID, userId)
                .setExpiration(accessTokenExpiresIn)
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();

        String refreshToken = Jwts.builder()
                .setExpiration(refreshTokenExpiresIn)
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();

        return TokenResponse.of(accessToken, refreshToken);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey).build()
                    .parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty", e);
        }
        return false;
    }

    public Long getUserIdFromJwt(String accessToken) {
        return parseClaims(accessToken).get(JwtConstants.USER_ID, Long.class);
    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey).build()
                    .parseClaimsJws(accessToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

}
