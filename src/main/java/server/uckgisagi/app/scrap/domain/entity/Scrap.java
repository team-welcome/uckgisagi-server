package server.uckgisagi.app.scrap.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import server.uckgisagi.common.domain.AuditingTimeEntity;
import server.uckgisagi.app.post.domain.entity.Post;
import server.uckgisagi.app.user.domain.entity.User;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Scrap extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scrap_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;     // 스크랩한 유저 아이디

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;     // 스크랩한 게시물 아이디

    private Scrap(final User user, final Post post) {
        this.user = user;
        this.post = post;
    }

    public static Scrap newInstance(User user, Post post) {
        return new Scrap(user, post);
    }
}
