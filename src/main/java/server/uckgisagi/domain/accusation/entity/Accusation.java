package server.uckgisagi.domain.accusation.entity;

import lombok.*;
import server.uckgisagi.app.accusation.dto.AccusationPostResDto;
import server.uckgisagi.domain.common.AuditingTimeEntity;
import server.uckgisagi.domain.post.entity.Post;
import server.uckgisagi.domain.user.entity.User;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Accusation extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accusation_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    public static Accusation newInstance(Post post, User user) {
        return Accusation.builder()
                .post(post)
                .user(user)
                .build();
    }

    public AccusationPostResDto toAccusePostResponseDto(Accusation accusation){
        return AccusationPostResDto.builder()
                .postId(accusation.getPost().getId())
                .userId(accusation.getUser().getId())
                .build();
    }

}
