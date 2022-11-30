package server.uckgisagi.domain.block.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import server.uckgisagi.domain.user.entity.User;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Block {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    private Long blockUserId;

    private Block(User user, Long blockUserId){
        this.user = user;
        this.blockUserId = blockUserId;
    }

    public static Block newInstance(User user, Long blockUserId){
        return new Block(user, blockUserId);
    }
}
