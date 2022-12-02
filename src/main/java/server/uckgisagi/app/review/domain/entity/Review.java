package server.uckgisagi.app.review.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import server.uckgisagi.app.store.domain.entity.Store;
import server.uckgisagi.app.user.domain.entity.User;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String comment;

    private Review(final Store store, final User user, final String comment) {
        this.store = store;
        this.user = user;
        this.comment = comment;
    }

    public static Review newInstance(Store store, User user, String comment) {
        return new Review(store, user, comment);
    }
}
