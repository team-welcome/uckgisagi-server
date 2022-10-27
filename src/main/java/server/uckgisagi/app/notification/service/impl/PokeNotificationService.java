package server.uckgisagi.app.notification.service.impl;

import com.google.api.core.ApiFuture;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.uckgisagi.app.notification.service.NotificationService;
import server.uckgisagi.domain.notification.entity.Notification;
import server.uckgisagi.domain.notification.entity.enumerate.NotificationType;
import server.uckgisagi.domain.notification.repository.NotificationRepository;
import server.uckgisagi.domain.user.repository.TokenRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class PokeNotificationService implements NotificationService {

    private final TokenRepository tokenRepository;
    private final NotificationRepository notificationRepository;

    @Override
    @Transactional
    public void sendNotification(Long userId, Long friendUserId) {
        String targetUserToken = tokenRepository.findFcmTokenByUserId(friendUserId);
        Message message = Message.builder()
                .putData("title", "쥰내 찌르기 알림 도착 ㅋㅋ")
                .putData("content", String.format("%s 님이 너를 쥰내게 찔렀어요 ㅋㅋ", userId))
                .setToken(targetUserToken)
                .build();
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

}
