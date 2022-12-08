package server.uckgisagi.app.notification;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import server.uckgisagi.app.notification.provider.NotificationServiceProvider;
import server.uckgisagi.app.notification.service.NotificationService;
import server.uckgisagi.app.user.domain.dictionary.UserDictionary;
import server.uckgisagi.app.user.service.UserServiceUtils;
import server.uckgisagi.common.dto.ApiSuccessResponse;
import server.uckgisagi.common.success.SuccessResponseResult;
import server.uckgisagi.config.interceptor.Auth;
import server.uckgisagi.config.resolver.LoginUserId;
import server.uckgisagi.app.notification.domain.entity.enumerate.NotificationType;
import server.uckgisagi.app.user.domain.repository.UserRepository;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationServiceProvider notificationServiceProvider;
    private final UserRepository userRepository;

    @ApiOperation("[인증] 친구 홈 페이지 - 친구가 오늘 올린 인증글이 없는 경우 '찌르기'")
    @Auth
    @PostMapping("/v1/notification/{friendUserId}/poke")
    public ApiSuccessResponse<SuccessResponseResult> sendPokeNotification(@PathVariable Long friendUserId, @ApiIgnore @LoginUserId Long userId) {
        NotificationService notificationService = notificationServiceProvider.getNotificationService(NotificationType.POKE);
        notificationService.sendNotification(userId, friendUserId, UserDictionary.from(List.of(
                UserServiceUtils.findByUserId(userRepository, userId),
                UserServiceUtils.findByUserId(userRepository, friendUserId))));
        return ApiSuccessResponse.success(SuccessResponseResult.CREATED_NOTIFICATION);
    }
}
