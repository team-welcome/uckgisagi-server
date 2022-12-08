package server.uckgisagi.app.notification.service;

import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import server.uckgisagi.app.notification.domain.entity.Notifications;
import server.uckgisagi.app.notification.domain.entity.enumerate.NotificationType;
import server.uckgisagi.app.notification.domain.repository.NotificationRepository;
import server.uckgisagi.app.user.domain.entity.User;
import server.uckgisagi.config.firebase.FirebaseInitializer;

import static server.uckgisagi.app.notification.constant.NotificationConstants.*;

public abstract class SendMessageService {

    protected void sendMessageByNotificationType(NotificationRepository notificationRepository, User user, User friend, NotificationType type, Logger log) {

        setCommonFields(notificationRepository, user, friend, log);
        switch (type) {
            case POKE:
                setPokeFields();
                break;
            case FOLLOW:
                setFollowFields();
                break;
        }
        sendAsyncFCM();
    }

    private void sendAsyncFCM() {
        ApiFutures.addCallback(
                FirebaseInitializer.getFirebaseMessaging().sendAsync(message),
                getApiFutureCallback(),
                MoreExecutors.directExecutor());
    }

    @NotNull
    private ApiFutureCallback<String> getApiFutureCallback() {
        return new ApiFutureCallback<String>() {
            @Override
            public void onFailure(Throwable t) {
                log.error(FAILURE_MESSAGE);
            }

            @Override
            public void onSuccess(String result) {
                notificationRepository.save(
                        Notifications.newInstance(userId, friendUserId, notificationType, notificationMessage)
                );
                log.info(SUCCESS_MESSAGE);
            }
        };
    }

    private Message getPokeMessage() {
        return Message.builder()
                .setToken(targetToken)
                .setNotification(getPokeNotification())
                .build();
    }

    private Notification getPokeNotification() {
        return new Notification(
                POKE_TITLE,
                String.format(POKE_BODY, userNickname)
        );
    }

    private Message getFollowMessage() {
        return Message.builder()
                .setToken(targetToken)
                .setNotification(getFollowNotification())
                .build();
    }

    private Notification getFollowNotification() {
        return new Notification(
                String.format(FOLLOW_TITLE, userNickname),
                String.format(FOLLOW_BODY, userNickname)
        );
    }

    private NotificationRepository notificationRepository;
    private Long userId;
    private String userNickname;
    private Long friendUserId;
    private String targetToken;
    private Logger log;

    private NotificationType notificationType;
    private Message message;
    private String notificationMessage;

    private void setFollowFields() {
        this.notificationType = NotificationType.FOLLOW;
        this.message = getFollowMessage();
        this.notificationMessage = String.format(FOLLOW_MESSAGE, userNickname);
    }

    private void setPokeFields() {
        this.notificationType = NotificationType.POKE;
        this.message = getPokeMessage();
        this.notificationMessage = String.format(POKE_MESSAGE, userNickname);
    }

    private void setCommonFields(NotificationRepository notificationRepository, User user, User friend, Logger log) {
        this.notificationRepository = notificationRepository;
        this.userId = user.getId();
        this.userNickname = user.getNickname();
        this.friendUserId = friend.getId();
        this.targetToken = friend.getUserFcmToken();
        this.log = log;
    }
}
