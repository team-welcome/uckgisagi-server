package server.uckgisagi.app.post.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import server.uckgisagi.app.post.domain.entity.enumerate.PostStatus;
import server.uckgisagi.app.post.domain.entity.Post;
import server.uckgisagi.app.user.domain.entity.User;

import java.time.LocalDate;
import java.util.List;

import static server.uckgisagi.app.post.domain.entity.QPost.*;

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

    @Override
    public Post findByPostIdAndUserId(Long postId, Long userId){
        return query
                .selectFrom(post)
                .where(
                        post.id.eq(postId),
                        post.user.id.eq(userId)
                )
                .fetchOne();
    }

    @Override
    public List<Post> findAllByPostStatus(List<Long> blockUserIds) {
        return query.selectFrom(post)
                .where(
                        post.postStatus.eq(PostStatus.ACTIVE),
                        post.user.id.notIn(blockUserIds)
                )
                .orderBy(post.createdAt.desc())
                .fetch();
    }

    public List<Post> findPostByDateAndUserId(LocalDate date, Long userId) {
        return query
                .selectFrom(post)
                .where(
                        post.user.id.eq(userId),
                        post.createdAt.between(
                                date.atStartOfDay(),
                                date.atStartOfDay().plusDays(ONE_DAY)
                        ))
                .fetch();
    }
}
