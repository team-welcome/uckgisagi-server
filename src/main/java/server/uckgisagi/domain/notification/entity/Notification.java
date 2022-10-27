package server.uckgisagi.domain.notification.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import server.uckgisagi.domain.common.AuditingTimeEntity;
import server.uckgisagi.domain.notification.entity.enumerate.NotificationType;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notification extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private Long userId;    // 알람을 보내는 유저 아이디

    @Column(nullable = false)
    private Long targetUserId;      // 알람을 받을 유저 아이디

    @Column(nullable = false, length = 20)
    private NotificationType type;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String message;

    @Builder(access = AccessLevel.PACKAGE)
    private Notification(final Long userId, final Long targetUserId, final NotificationType type, final String message) {
        this.userId = userId;
        this.targetUserId = targetUserId;
        this.type = type;
        this.message = message;
    }

    public static Notification newInstance(Long userId, Long targetUserId, NotificationType type, String message) {
        return Notification.builder()
                .userId(userId)
                .targetUserId(targetUserId)
                .type(type)
                .message(message)
                .build();
    }

}
