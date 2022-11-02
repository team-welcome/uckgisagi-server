package server.uckgisagi.domain.follow.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import server.uckgisagi.domain.follow.entity.Follow;
import server.uckgisagi.domain.user.entity.User;

import java.util.List;

import static server.uckgisagi.domain.follow.entity.QFollow.*;

@RequiredArgsConstructor
public class FollowRepositoryCustomImpl implements FollowRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public boolean existsByTargetUserIdAndUserId(Long targetUserId, Long userId) {
        return query
                .selectOne()
                .from(follow)
                .where(
                        follow.followee.id.eq(targetUserId),
                        follow.follower.id.eq(userId)
                ).fetchFirst() != null;
    }

    @Override
    public List<User> findMyFollowingUserByUserId(Long userId) {
        return query
                .select(follow.followee).distinct()
                .from(follow)
                .where(follow.follower.id.in(userId))
                .fetch();
    }

    @Override
    public Follow findFollowByFolloweeUserIdAndFollowerUserId(Long followeeUserId, Long followerUserId) {
        return query
                .selectFrom(follow)
                .where(
                        follow.followee.id.eq(followeeUserId),
                        follow.follower.id.eq(followerUserId)
                ).fetchOne();
    }

}
