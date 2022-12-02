package server.uckgisagi.app.notification.service;

import server.uckgisagi.app.user.domain.entity.User;

public interface NotificationService {
    void sendNotification(Long userId, User friendUserId);
}
