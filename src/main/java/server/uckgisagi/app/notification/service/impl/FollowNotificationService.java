package server.uckgisagi.app.notification.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.uckgisagi.app.notification.service.NotificationService;
import server.uckgisagi.app.notification.service.SendMessageService;
import server.uckgisagi.app.notification.domain.entity.enumerate.NotificationType;
import server.uckgisagi.app.notification.domain.repository.NotificationRepository;
import server.uckgisagi.app.user.domain.entity.User;

@Service
@RequiredArgsConstructor
public class FollowNotificationService extends SendMessageService implements NotificationService {

    private static final Logger log = LoggerFactory.getLogger(FollowNotificationService.class);

    private final NotificationRepository notificationRepository;

    @Override
    @Transactional
    public void sendNotification(Long userId, User friend) {
        sendMessageByNotificationType(notificationRepository, userId, friend, NotificationType.FOLLOW, log);
    }
}
