package server.uckgisagi.domain.user.repository;

import server.uckgisagi.domain.user.entity.User;
import server.uckgisagi.domain.user.entity.enumerate.SocialType;

import java.util.List;

public interface UserRepositoryCustom {
    User findUserByUserId(Long userId);
    List<User> findAllUserByNickname(String nickname, List<Long> blockUserIds);
    User findUserBySocialIdAndSocialType(String socialId, SocialType socialType);
    boolean existsBySocialIdAndSocialType(String socialId, SocialType socialType);
}
