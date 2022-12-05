package server.uckgisagi.app.user.domain.dictionary;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import server.uckgisagi.app.user.domain.entity.User;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDictionary {

    /**
     * 유저의 id 값으로 해당 유저를 저장하는 dictionary
     */
    private final Map<Long, User> dictionary;

    public static UserDictionary from(List<User> users) {
        return new UserDictionary(
                users.stream()
                        .collect(
                                Collectors.toMap(
                                        user -> user.getId(),
                                        user -> user
                                )
                        ));
    }

    public User getUserByUserId(Long userId) {
        return dictionary.get(userId);
    }
}
