package server.uckgisagi.app.notification.provider;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import server.uckgisagi.app.notification.service.NotificationService;
import server.uckgisagi.app.notification.service.impl.FollowNotificationService;
import server.uckgisagi.app.notification.service.impl.PokeNotificationService;
import server.uckgisagi.app.notification.domain.entity.enumerate.NotificationType;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class NotificationServiceProvider {

    private static final Map<NotificationType, NotificationService> notificationServiceMap = new HashMap<>();

    private final PokeNotificationService pokeNotificationService;
    private final FollowNotificationService followNotificationService;

    @PostConstruct
    public void initNotificationServiceMap() {
        notificationServiceMap.put(NotificationType.POKE, pokeNotificationService);
        notificationServiceMap.put(NotificationType.FOLLOW, followNotificationService);
    }

    public NotificationService getNotificationService(NotificationType notificationType) {
        return notificationServiceMap.get(notificationType);
    }
}
