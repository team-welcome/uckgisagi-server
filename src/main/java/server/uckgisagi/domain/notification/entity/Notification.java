package server.uckgisagi.domain.notification.entity;

import lombok.AccessLevel;
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
    private NotificationType notificationType;

//    @Column(columnDefinition = "TEXT", nullable = false)
//    private String notificationMessage;

}
