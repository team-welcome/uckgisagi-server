package server.uckgisagi.external.client.apple.dto;

import lombok.*;
import server.uckgisagi.common.exception.custom.ValidationException;

import java.util.List;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApplePublicKeyResponse {

    private List<JWKSetKey> keys;

    public JWKSetKey getMatchedPublicKey(String kid, String alg) {
        return keys.stream()
                .filter(key -> key.getKid().equals(kid) && key.getAlg().equals(alg))
                .findFirst()
                .orElseThrow(() -> new ValidationException("일치하는 Public Key 가 없습니다"));
    }

    @ToString
    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class JWKSetKey {
        private String alg;  // 토큰을 암호화하는 데 사용되는 암호화 알고리즘
        private String e;    // RSA 공개 키의 지수 값
        private String kid;  // 개발자 계정에서 얻은 10자리 식별자 키
        private String kty;  // 키 유형 매개변수 설정 : "RSA"로 설정해야함
        private String n;    // RSA 공개 키의 모듈러스 값
        private String use;  // 공개 키의 의도된 용도
    }

}