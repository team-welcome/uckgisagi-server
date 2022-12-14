package server.uckgisagi.app.post.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import server.uckgisagi.app.post.domain.entity.enumerate.PostStatus;
import server.uckgisagi.common.domain.AuditingTimeEntity;
import server.uckgisagi.app.user.domain.entity.User;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private PostStatus postStatus;

    @Builder(access = AccessLevel.PACKAGE)
    private Post(final User user, final String imageUrl, final String title, final String content) {
        this.user = user;
        this.imageUrl = imageUrl;
        this.title = title;
        this.content = content;
        this.postStatus = PostStatus.ACTIVE;
    }

    public static Post newInstance(User user, String imageUrl, String title, String content) {
        return Post.builder()
                .user(user)
                .imageUrl(imageUrl)
                .title(title)
                .content(content)
                .build();
    }

    public void changeStatus(PostStatus postStatus){
        this.postStatus = postStatus;
    }
}
