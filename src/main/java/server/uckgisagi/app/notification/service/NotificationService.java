package server.uckgisagi.app.notification.service;

import server.uckgisagi.domain.user.entity.User;

public interface NotificationService {
    void sendNotification(Long userId, User friendUserId);
}
