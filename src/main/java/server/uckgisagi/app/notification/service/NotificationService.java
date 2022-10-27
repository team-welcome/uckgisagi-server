package server.uckgisagi.app.notification.service;

public interface NotificationService {
    void sendNotification(Long userId, Long friendUserId);
}
