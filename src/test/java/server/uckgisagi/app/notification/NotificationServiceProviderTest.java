package server.uckgisagi.app.notification;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import server.uckgisagi.app.notification.provider.NotificationServiceProvider;
import server.uckgisagi.app.notification.service.NotificationService;
import server.uckgisagi.app.notification.service.impl.FollowNotificationService;
import server.uckgisagi.app.notification.service.impl.PokeNotificationService;
import server.uckgisagi.domain.notification.entity.enumerate.NotificationType;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class NotificationServiceProviderTest {

    @Autowired
    private NotificationServiceProvider notificationServiceProvider;

    private static final NotificationType POKE_TYPE = NotificationType.POKE;
    private static final NotificationType FOLLOW_TYPE = NotificationType.FOLLOW;

    @DisplayName("NotificationServiceProvider 가 성공적으로 등록된다")
    @Test
    void AuthServiceProvider_register_success() {
        NotificationService notificationService1 = notificationServiceProvider.getNotificationService(POKE_TYPE);
        NotificationService notificationService2 = notificationServiceProvider.getNotificationService(FOLLOW_TYPE);

        assertTrue(notificationService1 instanceof PokeNotificationService);
        assertTrue(notificationService2 instanceof FollowNotificationService);
    }
}
