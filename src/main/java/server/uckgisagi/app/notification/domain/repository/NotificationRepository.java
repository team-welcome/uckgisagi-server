package server.uckgisagi.app.notification.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.uckgisagi.app.notification.domain.entity.Notifications;

public interface NotificationRepository extends JpaRepository<Notifications, Long> {
}
