package server.uckgisagi.app.auth.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import server.uckgisagi.app.auth.dto.response.AppleIdTokenResponse;

import java.io.File;
import java.io.FileReader;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.List;


@Component
public class GenerateAppleKey {

    private static  final String audience="https://appleid.apple.com";
    private static  final String issuer="XXXXXXXXX";
    private static  final String subject="com.test.apple.login";

    public String generateSecretKey() throws Exception {
        PrivateKey pKey = generatePrivateKey();
        return Jwts.builder()
                .setHeaderParam(JwsHeader.KEY_ID, "XXXXXXXXX")
                .setIssuer(issuer)
                .setAudience(audience)
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + (10000 * 60 * 5)))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.ES256, pKey)
                .compact();
    }

    private PrivateKey generatePrivateKey() throws Exception {
        File file = ResourceUtils.getFile("C:\\src\\main\\java\\cert.p8");
        final PEMParser pemParser = new PEMParser(new FileReader(file));
        final JcaPEMKeyConverter converter = new JcaPEMKeyConverter();
        final PrivateKeyInfo object = (PrivateKeyInfo) pemParser.readObject();
        final PrivateKey pKey = converter.getPrivateKey(object);
        pemParser.close();
        return pKey;
    }

    public String createPublicKeyApple(AppleIdTokenResponse appleIdTokenResponse, List<JWKSetKeys> keysList) throws NoSuchAlgorithmException, InvalidKeySpecException {

        JWKSetKeys applePublicKey = null;
        String emailid = null;
        for (JWKSetKeys keys : keysList) {
            applePublicKey = keys;
            BigInteger modulus = new BigInteger(1, Base64.getUrlDecoder().decode(applePublicKey.getN()));
            BigInteger exponent = new BigInteger(1, Base64.getUrlDecoder().decode(applePublicKey.getE()));
            PublicKey publicKey = KeyFactory.getInstance(applePublicKey.getKty()).generatePublic(new RSAPublicKeySpec(modulus, exponent));
            try {
                Claims claims = getClaims(publicKey, appleIdTokenResponse);
                System.out.println("");
                return claims.get("email", String.class);
            } catch (Exception exception) {
                System.out.println("Trying for another key");
            }
        }

        return "Error Occured...";
    }

    private Claims getClaims(PublicKey publicKey, AppleIdTokenResponse appleIdTokenResponse) {
        return Jwts.parser()
                .setSigningKey(publicKey)
                .parseClaimsJws(appleIdTokenResponse.getId_token()).getBody();
    }

}
