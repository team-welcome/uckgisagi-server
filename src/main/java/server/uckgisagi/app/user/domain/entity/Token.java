package server.uckgisagi.app.user.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import server.uckgisagi.common.domain.AuditingTimeEntity;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Token extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String fcmToken;

    private Token(final Long userId, final String fcmToken) {
        this.userId = userId;
        this.fcmToken = fcmToken;
    }

    public static Token newInstance(Long userId, String fcmToken) {
        return new Token(userId, fcmToken);
    }
}
