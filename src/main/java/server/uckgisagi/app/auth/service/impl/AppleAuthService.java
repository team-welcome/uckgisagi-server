package server.uckgisagi.app.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.uckgisagi.app.auth.dto.request.LoginDto;
import server.uckgisagi.app.auth.service.AuthService;
import server.uckgisagi.app.user.service.UserService;
import server.uckgisagi.app.user.service.UserServiceUtils;
import server.uckgisagi.common.util.RandomNicknameUtils;
import server.uckgisagi.app.user.domain.entity.User;
import server.uckgisagi.app.user.domain.entity.enumerate.SocialType;
import server.uckgisagi.app.user.domain.repository.UserRepository;
import server.uckgisagi.external.client.apple.AppleTokenDecoder;

@Service
@RequiredArgsConstructor
public class AppleAuthService implements AuthService {

    private static final SocialType SOCIAL_TYPE = SocialType.APPLE;

    private final AppleTokenDecoder appleTokenDecoder;
    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public User login(LoginDto request) {
        String socialId = appleTokenDecoder.getSocialIdFromIdToken(request.getSocialAccessToken());
        User user = UserServiceUtils.findUserBySocialIdAndSocialType(userRepository, socialId, SOCIAL_TYPE);
        if (user == null) {
            return userService.registerUser(request.toCreateUserDto(socialId, RandomNicknameUtils.generate(), request.getFcmToken()));
        }
        return user;
    }
}
