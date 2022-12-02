package server.uckgisagi.app.notification.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.uckgisagi.app.notification.domain.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
