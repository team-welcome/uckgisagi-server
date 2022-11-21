package server.uckgisagi.domain.accusation.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import server.uckgisagi.domain.accusation.entity.Accusation;

import java.util.List;
import java.util.Optional;

import static server.uckgisagi.domain.accusation.QAccusation.accusation;

@RequiredArgsConstructor
public class AccusationCustomRepositoryImpl implements AccusationCustomRepository {

    private final JPAQueryFactory query;


    @Override
    public List<Accusation> findAllByPostId(Long postId) {
        return query.selectFrom(accusation)
                .where(accusation.post.id.eq(postId))
                .fetch();
    }

    @Override
    public Optional<Accusation> findByUserIdAndPostId(Long userId, Long postId) {
        return query.selectFrom(accusation)
                .where(accusation.user.id.eq(userId))
                .where(accusation.post.id.eq(postId))
                .stream().findFirst();
    }
}
