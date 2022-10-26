package server.uckgisagi.domain.follow.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import server.uckgisagi.domain.user.entity.User;

import java.util.List;

import static server.uckgisagi.domain.follow.entity.QFollow.*;

@RequiredArgsConstructor
public class FollowRepositoryCustomImpl implements FollowRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public List<User> findMyFollowingUserByUserId(Long userId) {
        return query
                .select(follow.followee).distinct()
                .from(follow)
                .where(follow.follower.id.in(userId))
                .fetch();
    }

}