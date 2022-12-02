package server.uckgisagi.app.accusation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import server.uckgisagi.app.accusation.domain.entity.Accusation;
import server.uckgisagi.app.post.domain.entity.Post;
import server.uckgisagi.app.user.domain.entity.User;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccusationPostReqDto {

    private Long postId;

    public Accusation toAccusationEntity(User user, Post post){
        return Accusation.newInstance(post, user);
    }
}
