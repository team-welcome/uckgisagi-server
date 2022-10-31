package server.uckgisagi.app.auth.utils;

import lombok.Data;

@Data
public class JWKSetKeys {
    public String kty;
    public String kid;
    public String use;
    public String alg;
    public String n;
    public String e;
}
