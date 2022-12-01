package server.uckgisagi.domain.block.entity;

import lombok.*;
import server.uckgisagi.domain.user.entity.User;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "block_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    private Long blockUserId;

    private Block(final User user, final Long blockUserId){
        this.user = user;
        this.blockUserId = blockUserId;
    }

    public static Block newInstance(User user, Long blockUserId){
        return new Block(user, blockUserId);
    }
}
