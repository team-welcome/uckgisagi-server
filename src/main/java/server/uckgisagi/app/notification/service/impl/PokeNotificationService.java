package server.uckgisagi.app.notification.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.uckgisagi.app.notification.service.NotificationService;
import server.uckgisagi.app.notification.service.SendMessageService;
import server.uckgisagi.domain.notification.entity.enumerate.NotificationType;
import server.uckgisagi.domain.notification.repository.NotificationRepository;
import server.uckgisagi.domain.user.entity.User;

@Service
@RequiredArgsConstructor
public class PokeNotificationService extends SendMessageService implements NotificationService {

    private static final Logger log = LoggerFactory.getLogger(PokeNotificationService.class);

    private final NotificationRepository notificationRepository;

    @Override
    @Transactional
    public void sendNotification(Long userId, User friend) {
        sendMessageByNotificationType(notificationRepository, userId, friend, NotificationType.POKE, log);
    }

}
