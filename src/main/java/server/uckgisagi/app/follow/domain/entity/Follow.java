package server.uckgisagi.app.follow.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import server.uckgisagi.common.domain.AuditingTimeEntity;
import server.uckgisagi.app.user.domain.entity.User;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Follow extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "followee_id")
    private User followee;  // 팔로우 당하는 유저

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id")
    private User follower;  // 팔로우 하는 유저

    private Follow(final User followee, final User follower) {
        this.followee = followee;
        this.follower = follower;
    }

    public static Follow newInstance(User followee, User follower) {
        return new Follow(followee, follower);
    }
}
