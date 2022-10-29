package server.uckgisagi.app.notification.service;

import com.google.api.core.ApiFuture;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import org.slf4j.Logger;
import server.uckgisagi.domain.notification.entity.Notification;
import server.uckgisagi.domain.notification.entity.enumerate.NotificationType;
import server.uckgisagi.domain.notification.repository.NotificationRepository;
import server.uckgisagi.domain.user.entity.User;

public abstract class SendMessageService {

    protected void sendMessageByNotificationType(NotificationRepository notificationRepository, Long userId,
                                                 User friend, NotificationType type, Logger log) {
        switch (type) {
            case POKE:
                sendAsyncFCM(
                        notificationRepository,
                        getPokeMessage(userId, friend.getUserFcmToken()),
                        userId,
                        friend.getId(),
                        log);
                break;
            case FOLLOW:
                sendAsyncFCM(
                        notificationRepository,
                        getFollowMessage(userId, friend.getUserFcmToken()),
                        userId,
                        friend.getId(),
                        log);
                break;
        }
    }

    private void sendAsyncFCM(NotificationRepository notificationRepository, Message message,
                              Long userId, Long friendUserId, Logger log) {
        ApiFuture<String> sendAsync = FirebaseMessaging.getInstance().sendAsync(message);

        if (sendAsync.isDone()) {
            log.info("✅ 푸시알림 전송 완료 ✅");
            notificationRepository.save(Notification.newInstance(
                    userId, friendUserId, NotificationType.POKE, message.toString())
            );
        } else if (sendAsync.isCancelled()) {
            log.info("❌ 푸시알림 전송 실패 ❌");
        }
    }

    private Message getPokeMessage(Long userId, String targetUserToken) {
        return Message.builder()
                .putData("title", "쥰내 찌르기 알림 도착 ㅋㅋ")
                .putData("content", String.format("%s 님이 너를 쥰내게 찔렀어요 ㅋㅋ", userId))
                .setToken(targetUserToken)
                .build();
    }

    private Message getFollowMessage(Long userId, String targetUserToken) {
        return Message.builder()
                .putData("title", "쥰내 팔로우 알림 도착 ㅋㅋ")
                .putData("content", String.format("%s 님이 너를 쥰내게 팔로우했어요 ㅋㅋ", userId))
                .setToken(targetUserToken)
                .build();
    }

}
