package server.uckgisagi.domain.user.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import server.uckgisagi.domain.user.entity.User;
import server.uckgisagi.domain.user.entity.enumerate.SocialType;

import java.util.List;

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

    @Override
    public List<User> findAllUserByNickname(String nickname, User loginUser) {
        return query
                .selectFrom(user).distinct()
                .where(user.nickname.contains(nickname))
                .where((Predicate) loginUser.getBlocks().stream().filter(block -> !block.getBlockUserId().equals(user.id))) // 현재 로그인 한 유저가 차단한 id와 같지 않은 같지 않은 userId를 가진 유저만 반환.
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
