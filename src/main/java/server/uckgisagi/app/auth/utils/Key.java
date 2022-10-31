package server.uckgisagi.app.auth.utils;

import lombok.Data;

import java.util.List;

@Data
public class Key {
    public List<JWKSetKeys> keys;
}
