package server.uckgisagi.domain.accusation.entity;

import lombok.*;
import server.uckgisagi.app.accusation.dto.AccusePostResponseDto;
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

    public AccusePostResponseDto toAccusePostResponseDto(Accusation accusation){
        return AccusePostResponseDto.builder()
                .postId(accusation.getPost().getId())
                .userId(accusation.getUser().getId())
                .build();
    }

}
