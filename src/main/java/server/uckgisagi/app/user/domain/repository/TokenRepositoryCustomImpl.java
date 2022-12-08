package server.uckgisagi.app.user.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static server.uckgisagi.app.user.domain.entity.QToken.*;

@RequiredArgsConstructor
public class TokenRepositoryCustomImpl implements TokenRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public String findFcmTokenByUserId(Long userId) {
        return query
                .select(token.fcmToken).distinct()
                .from(token)
                .where(token.userId.eq(userId))
                .fetchFirst();
    }
}
