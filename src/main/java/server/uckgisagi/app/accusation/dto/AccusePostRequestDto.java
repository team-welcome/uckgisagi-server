package server.uckgisagi.app.accusation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import server.uckgisagi.domain.accusation.entity.Accusation;
import server.uckgisagi.domain.post.entity.Post;
import server.uckgisagi.domain.user.entity.User;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccusePostRequestDto {

    private Long postId;

    public Accusation toAccusationEntity(User user, Post post){
        return Accusation.builder()
                .user(user)
                .post(post)
                .build();
    }
}
