package server.uckgisagi.app.scrap.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import server.uckgisagi.app.post.domain.entity.Post;
import server.uckgisagi.app.post.domain.entity.enumerate.PostStatus;
import server.uckgisagi.app.scrap.domain.entity.Scrap;

import java.util.List;

import static server.uckgisagi.app.scrap.domain.entity.QScrap.*;

@RequiredArgsConstructor
public class ScrapRepositoryCustomImpl implements ScrapRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public List<Post> findScrapPostByUserId(Long userId, List<Long> blockUserIds) {
        return query
                .select(scrap.post).distinct()
                .from(scrap)
                .where(
                        scrap.post.postStatus.eq(PostStatus.ACTIVE),
                        scrap.post.user.id.notIn(blockUserIds),
                        scrap.user.id.eq(userId)
                )
                .fetch();
    }

    @Override
    public boolean existsByPostAndUserId(Post post, Long userId) {
        return query
                .selectOne()
                .from(scrap)
                .where(
                        scrap.post.eq(post),
                        scrap.user.id.eq(userId),
                        scrap.post.postStatus.eq(PostStatus.ACTIVE)
                ).fetchFirst() != null;
    }

    @Override
    public Scrap findScrapByPostIdAndUserId(Long postId, Long userId) {
        return query
                .selectFrom(scrap)
                .where(
                        scrap.user.id.eq(userId),
                        scrap.post.id.eq(postId)
                ).fetchOne();
    }
}
