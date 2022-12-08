package server.uckgisagi.app.notification.service;

import server.uckgisagi.app.user.domain.dictionary.UserDictionary;

public interface NotificationService {
    void sendNotification(Long userId, Long targetUserId, UserDictionary dictionary);
}
