package server.uckgisagi.domain.post.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import server.uckgisagi.domain.post.entity.Post;
import server.uckgisagi.domain.post.entity.enumerate.PostStatus;
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

    @Override
    public Post findByPostIdAndUserId(Long postId, Long userId){
        return query.selectFrom(post)
                .where(post.id.eq(postId))
                .where(post.user.id.eq((userId)))
                .fetchOne();
    }

    @Override
    public List<Post> findAllByPostStatus(User loginUser) {
        return query.selectFrom(post)
                .where(post.postStatus.eq(PostStatus.ACTIVE))
                .where((Predicate) loginUser.getBlocks().stream().filter(block -> block.getBlockUserId().equals(post.user.id))) // post를 쓴 user의 id가 현재 로그인한 user의 차단 리스트에 있는 id와 같지 않아야 함.
                .orderBy(post.createdAt.desc())
                .fetch();
    }

}
