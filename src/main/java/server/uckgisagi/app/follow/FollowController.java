package server.uckgisagi.app.follow;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import server.uckgisagi.app.follow.service.FollowService;
import server.uckgisagi.app.notification.provider.NotificationServiceProvider;
import server.uckgisagi.app.notification.service.NotificationService;
import server.uckgisagi.common.dto.ApiSuccessResponse;
import server.uckgisagi.common.success.SuccessResponseResult;
import server.uckgisagi.config.interceptor.Auth;
import server.uckgisagi.config.resolver.LoginUserId;
import server.uckgisagi.app.notification.domain.entity.enumerate.NotificationType;
import springfox.documentation.annotations.ApiIgnore;

import static server.uckgisagi.common.success.SuccessResponseResult.*;

@RestController
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;
    private final NotificationServiceProvider notificationServiceProvider;

    @ApiOperation("[인증] 유저 검색 페이지 - 팔로우 신청하기")
    @Auth
    @PostMapping("/v1/follow/{targetUserId}")
    public ApiSuccessResponse<SuccessResponseResult> followUser(@PathVariable Long targetUserId, @ApiIgnore @LoginUserId Long userId) {
        NotificationService notificationService = notificationServiceProvider.getNotificationService(NotificationType.FOLLOW);
        notificationService.sendNotification(userId, targetUserId, followService.followUser(targetUserId, userId));
        return ApiSuccessResponse.success(CREATED_NOTIFICATION);
    }

    @ApiOperation("[인증] 유저 검색 페이지 - 언팔로우 하기")
    @Auth
    @DeleteMapping("/v1/unfollow/{targetUserId}")
    public ApiSuccessResponse<SuccessResponseResult> unfollowUser(@PathVariable Long targetUserId, @ApiIgnore @LoginUserId Long userId) {
        followService.unfollowUser(targetUserId, userId);
        return ApiSuccessResponse.success(NO_CONTENT_UNFOLLOW_USER);
    }
}
