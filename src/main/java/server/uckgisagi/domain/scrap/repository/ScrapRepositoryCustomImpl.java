package server.uckgisagi.domain.scrap.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import server.uckgisagi.domain.post.entity.Post;
import server.uckgisagi.domain.post.entity.enumerate.PostStatus;
import server.uckgisagi.domain.scrap.entity.Scrap;
import server.uckgisagi.domain.user.entity.User;

import java.util.List;

import static server.uckgisagi.domain.scrap.entity.QScrap.*;

@RequiredArgsConstructor
public class ScrapRepositoryCustomImpl implements ScrapRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public List<Post> findScrapPostByUserId(Long userId, User loginUser) {
        return query
                .select(scrap.post).distinct()
                .from(scrap)
                .where(scrap.post.postStatus.eq(PostStatus.ACTIVE))
                .where((Predicate) loginUser.getBlocks().stream().filter(block -> !block.getBlockUserId().equals(scrap.post.user.id))) // scrap한 포스트를 쓴 user의 id가 현재 로그인한 user의 차단 리스트에 없어야 함.
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
