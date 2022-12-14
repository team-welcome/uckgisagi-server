package server.uckgisagi.app.accusation.domain.entity;

import lombok.*;
import server.uckgisagi.app.accusation.dto.AccusationPostResDto;
import server.uckgisagi.common.domain.AuditingTimeEntity;
import server.uckgisagi.app.post.domain.entity.Post;
import server.uckgisagi.app.user.domain.entity.User;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Accusation extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accusation_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;


    private Accusation(final Post post, final User user) {
        this.post = post;
        this.user = user;
    }

    public static Accusation newInstance(Post post, User user) {
        return new Accusation(post, user);
    }

    public AccusationPostResDto toAccusePostResponseDto(Accusation accusation){
        return AccusationPostResDto.builder()
                .postId(accusation.getPost().getId())
                .userId(accusation.getUser().getId())
                .build();
    }
}
