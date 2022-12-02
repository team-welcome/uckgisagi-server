package server.uckgisagi.app.user.domain.repository;

import server.uckgisagi.app.user.domain.entity.enumerate.SocialType;
import server.uckgisagi.app.user.domain.entity.User;

import java.util.List;

public interface UserRepositoryCustom {
    User findUserByUserId(Long userId);
    List<User> findAllUserByNickname(String nickname, List<Long> blockUserIds);
    User findUserBySocialIdAndSocialType(String socialId, SocialType socialType);
    boolean existsBySocialIdAndSocialType(String socialId, SocialType socialType);
}
