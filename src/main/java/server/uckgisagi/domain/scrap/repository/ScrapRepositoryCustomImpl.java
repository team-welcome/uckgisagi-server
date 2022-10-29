package server.uckgisagi.domain.scrap.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import server.uckgisagi.domain.post.entity.Post;

import java.util.List;

import static server.uckgisagi.domain.scrap.entity.QScrap.*;

@RequiredArgsConstructor
public class ScrapRepositoryCustomImpl implements ScrapRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public List<Post> findScrapPostByUserId(Long userId) {
        return query
                .select(scrap.post).distinct()
                .from(scrap)
                .where(scrap.user.id.eq(userId))
                .fetch();
    }

    @Override
    public boolean existsByPostAndUserId(Post post, Long userId) {
        return query
                .selectOne()
                .from(scrap)
                .where(
                        scrap.post.eq(post),
                        scrap.user.id.eq(userId)
                ).fetchFirst() != null;
    }

}
