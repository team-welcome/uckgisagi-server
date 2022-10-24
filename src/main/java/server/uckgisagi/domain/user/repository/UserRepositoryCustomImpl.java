package server.uckgisagi.domain.user.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import server.uckgisagi.domain.user.entity.User;

import static server.uckgisagi.domain.user.entity.QUser.*;

@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public User findUserByUserId(Long userId) {
        return query
                .selectFrom(user)
                .where(user.id.eq(userId))
                .fetchOne();
    }

}
