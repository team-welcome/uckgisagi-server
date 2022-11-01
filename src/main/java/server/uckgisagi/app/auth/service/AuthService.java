package server.uckgisagi.app.auth.service;

import server.uckgisagi.app.auth.dto.request.LoginDto;
import server.uckgisagi.domain.user.entity.User;

public interface AuthService {
    User login(LoginDto request);
}
