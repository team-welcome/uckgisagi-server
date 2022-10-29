package server.uckgisagi.app.follow;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import server.uckgisagi.app.follow.dto.request.FollowRequest;
import server.uckgisagi.app.follow.service.FollowService;
import server.uckgisagi.app.notification.provider.NotificationServiceProvider;
import server.uckgisagi.app.notification.service.NotificationService;
import server.uckgisagi.common.dto.ApiSuccessResponse;
import server.uckgisagi.common.success.SuccessResponseResult;
import server.uckgisagi.domain.notification.entity.enumerate.NotificationType;

import javax.validation.Valid;

import static server.uckgisagi.common.success.SuccessResponseResult.*;

@RestController
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;
    private final NotificationServiceProvider notificationServiceProvider;

    @ApiOperation("[인증] 유저 검색 페이지 - 팔로우 신청하기")
    @PostMapping("/v1/follow")
    public ApiSuccessResponse<SuccessResponseResult> followUser(@Valid @RequestBody FollowRequest request, Long userId) {
        NotificationService notificationService = notificationServiceProvider.getNotificationService(NotificationType.FOLLOW);
        notificationService.sendNotification(userId, followService.followUser(request, userId));
        return ApiSuccessResponse.success(CREATED_NOTIFICATION);
    }

    @ApiOperation("[인증] 유저 검색 페이지 - 언팔로우 하기")
    @DeleteMapping("/v1/unfollow")
    public ApiSuccessResponse<SuccessResponseResult> unfollowUser(@Valid @RequestBody FollowRequest request, Long userId) {
        followService.unfollowUser(request, userId);
        return ApiSuccessResponse.success(NO_CONTENT_UNFOLLOW_USER);
    }

}
