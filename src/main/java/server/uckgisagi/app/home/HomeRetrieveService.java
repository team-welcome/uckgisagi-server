package server.uckgisagi.app.home;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.uckgisagi.app.home.dto.response.HomeResponse;
import server.uckgisagi.app.home.dto.response.TodayPostStatus;
import server.uckgisagi.app.home.dto.response.UserResponseDto;
import server.uckgisagi.app.post.dto.response.PostResponse;
import server.uckgisagi.app.user.service.UserServiceUtils;
import server.uckgisagi.domain.follow.repository.FollowRepository;
import server.uckgisagi.domain.post.entity.Post;
import server.uckgisagi.domain.post.repository.PostRepository;
import server.uckgisagi.domain.user.entity.User;
import server.uckgisagi.domain.user.repository.UserRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HomeRetrieveService {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;
    private final PostRepository postRepository;

    private final LocalDate TODAY_DATE = LocalDate.now(ZoneId.of("Asia/Seoul"));
    private final int START_DAY_OF_MONTH = 1;
    private final long ONE_MONTH = 1L;

    @Transactional(readOnly = true)
    public HomeResponse retrieveMyHomeContents(Long userId) {
        return getHomeResponse(userId, postRepository.findPostByUserId(userId));
    }

    @Transactional(readOnly = true)
    public HomeResponse retrieveFriendHomeContents(Long userId, Long friendUserId) {
        return getHomeResponse(userId, postRepository.findPostByUserId(friendUserId));
    }

    private HomeResponse getHomeResponse(Long userId, List<Post> postByUserId) {
        UserResponseDto myInfoResponseDto = getMyInfoResponseDto(userId);
        List<UserResponseDto> friendsInfoResponseDto = getFriendsInfoResponseDto(userId);

        List<LocalDate> postDatesInThisMonth = getPostDatesInThisMonth(postByUserId);
        List<PostResponse> postResponses = getPostResponses(postByUserId);

        return HomeResponse.of(myInfoResponseDto, friendsInfoResponseDto, postDatesInThisMonth, postResponses);
    }

    @Nullable
    private List<LocalDate> getPostDatesInThisMonth(List<Post> posts) {
        final LocalDate THIS_MONTH_DATE = LocalDate.of(TODAY_DATE.getYear(), TODAY_DATE.getMonthValue(), START_DAY_OF_MONTH);
        return posts.stream().map(post -> {
                    LocalDate postCreatedAt = post.getCreatedAt().toLocalDate();
                    if (postCreatedAt.isAfter(THIS_MONTH_DATE)
                            && postCreatedAt.isBefore(THIS_MONTH_DATE.plusMonths(ONE_MONTH))) {
                        return postCreatedAt;
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @NotNull
    private List<PostResponse> getPostResponses(List<Post> posts) {
        return posts.stream()
                .map(PostResponse::from)
                .collect(Collectors.toList());
    }

    private List<UserResponseDto> getFriendsInfoResponseDto(Long userId) {
        List<User> friends = followRepository.findMyFollowingUserByUserId(userId);
        List<Long> friendsIds = friends.stream()
                .map(User::getId)
                .collect(Collectors.toList());
        List<User> todayPostUsers = postRepository.findUserIdsByTodayDate(TODAY_DATE, friendsIds);

        return friends.stream().map(friend -> {
            if (todayPostUsers.contains(friend)) {
                return UserResponseDto.of(friend, TodayPostStatus.ACTIVE);
            }
            return UserResponseDto.of(friend, TodayPostStatus.INACTIVE);
        }).collect(Collectors.toList());
    }

    private UserResponseDto getMyInfoResponseDto(Long userId) {
        TodayPostStatus todayPostStatus = TodayPostStatus.INACTIVE;
        if (postRepository.existsByTodayDate(TODAY_DATE, userId)) {
            todayPostStatus = TodayPostStatus.ACTIVE;
        }
        return UserResponseDto.of(UserServiceUtils.findByUserId(userRepository, userId), todayPostStatus);
    }

}
