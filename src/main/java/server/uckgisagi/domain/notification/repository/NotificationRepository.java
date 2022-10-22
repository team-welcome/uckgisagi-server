package server.uckgisagi.domain.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.uckgisagi.domain.notification.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
