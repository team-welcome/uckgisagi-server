package server.uckgisagi.app.user.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import server.uckgisagi.app.user.domain.entity.enumerate.SocialType;
import server.uckgisagi.app.user.domain.entity.User;

import java.util.List;

import static server.uckgisagi.app.user.domain.entity.QUser.*;

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

    @Override
    public List<User> findAllUserByNickname(String nickname, List<Long> blockUserIds) {
        return query
                .selectFrom(user).distinct()
                .where(
                        user.nickname.contains(nickname),
                        user.id.notIn(blockUserIds)
                )
                .fetch();
    }

    @Override
    public User findUserBySocialIdAndSocialType(String socialId, SocialType socialType) {
        return query
                .selectFrom(user)
                .where(
                        user.socialInfo.socialId.eq(socialId),
                        user.socialInfo.socialType.eq(socialType)
                ).fetchOne();
    }

    @Override
    public boolean existsBySocialIdAndSocialType(String socialId, SocialType socialType) {
        return query
                .selectOne()
                .from(user)
                .where(
                        user.socialInfo.socialId.eq(socialId),
                        user.socialInfo.socialType.eq(socialType)
                ).fetchFirst() != null;
    }
}
