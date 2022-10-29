package server.uckgisagi.domain.post.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import server.uckgisagi.domain.post.entity.Post;
import server.uckgisagi.domain.user.entity.User;

import java.time.LocalDate;
import java.util.List;

import static server.uckgisagi.domain.post.entity.QPost.*;

@RequiredArgsConstructor
public class PostRepositoryCustomImpl implements PostRepositoryCustom {

    private final JPAQueryFactory query;

    private static final long ONE_DAY = 1L;

    @Override
    public Post findPostByPostId(Long postId) {
        return query
                .selectFrom(post)
                .where(post.id.eq(postId))
                .fetchFirst();
    }

    @Override
    public List<Post> findPostByUserId(Long userId) {
        return query
                .selectFrom(post)
                .where(post.user.id.eq(userId))
                .fetch();
    }

    @Override
    public boolean existsByTodayDate(LocalDate today, Long userId) {
        return query
                .selectOne()
                .from(post)
                .where(
                        post.user.id.eq(userId),
                        post.createdAt.between(
                                today.atStartOfDay(),
                                today.atStartOfDay().plusDays(ONE_DAY)
                        ))
                .fetchFirst() != null;
    }


    @Override
    public List<User> findUserIdsByTodayDate(LocalDate today, List<Long> userIds) {
        return query
                .select(post.user)
                .from(post)
                .where(
                        post.user.id.in(userIds),
                        post.createdAt.between(
                                today.atStartOfDay(),
                                today.atStartOfDay().plusDays(ONE_DAY)
                        ))
                .fetch();
    }

}
