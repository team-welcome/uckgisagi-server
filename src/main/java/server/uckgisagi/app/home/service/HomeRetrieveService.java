package server.uckgisagi.app.home.service;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.uckgisagi.app.home.dto.response.*;
import server.uckgisagi.app.post.dto.response.PostResponse;
import server.uckgisagi.app.user.service.UserServiceUtils;
import server.uckgisagi.domain.follow.repository.FollowRepository;
import server.uckgisagi.domain.post.entity.Post;
import server.uckgisagi.domain.post.repository.PostRepository;
import server.uckgisagi.domain.user.entity.User;
import server.uckgisagi.domain.user.repository.UserRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HomeRetrieveService implements HomeService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final FollowRepository followRepository;

    private final LocalDate TODAY_DATE = LocalDate.now(ZoneId.of("Asia/Seoul"));
    private final LocalDate THIS_MONTH_DATE = LocalDate.of(TODAY_DATE.getYear(), TODAY_DATE.getMonthValue(), START_DAY_OF_MONTH);

    private static final int START_DAY_OF_MONTH = 1;
    private static final long ONE_MONTH = 1L;

    @Override
    @Transactional(readOnly = true)
    public HomeUserResponse retrieveMeAndFriendInfo(Long userId) {
        User user = UserServiceUtils.findByUserId(userRepository, userId);

        UserResponseDto myInfoResponseDto = getMyInfoResponseDto(user);
        List<UserResponseDto> friendsInfoResponseDto = getFriendsInfoResponseDto(user);

        return HomeUserResponse.of(myInfoResponseDto, friendsInfoResponseDto);
    }

    private List<UserResponseDto> getFriendsInfoResponseDto(User user) {
//        List<User> friends = user.getMyFollowings();
        List<User> friends = followRepository.findMyFollowingUserByUserId(user.getId());
        List<Long> friendsIds = friends.stream()
                .map(User::getId)
                .collect(Collectors.toList());
        List<User> todayPostUsers = postRepository.findUserIdsByTodayDate(TODAY_DATE, friendsIds);

        return friends.stream()
                .map(friend -> todayPostUsers.contains(friend)
                        ? UserResponseDto.of(friend, TodayPostStatus.ACTIVE)
                        : UserResponseDto.of(friend, TodayPostStatus.INACTIVE)
                )
                .collect(Collectors.toList());
    }

    private UserResponseDto getMyInfoResponseDto(User user) {
        return UserResponseDto.of(
                user,
                postRepository.existsByTodayDate(TODAY_DATE, user.getId())
                        ? TodayPostStatus.ACTIVE
                        : TodayPostStatus.INACTIVE
        );
    }

    @Override
    @Transactional(readOnly = true)
    public HomePostResponse retrieveHomeContents(Long userId) {
        User user = UserServiceUtils.findByUserId(userRepository, userId);
        return getHomePostResponse(postRepository.findPostByUserId(user.getId()));
    }

    private HomePostResponse getHomePostResponse(List<Post> postByUserId) {
        return HomePostResponse.of(
                getPostDatesInThisMonth(postByUserId),
                getPostResponses(postByUserId)
        );
    }

    @Nullable
    private List<LocalDate> getPostDatesInThisMonth(List<Post> posts) {
        return posts.stream()
                .map(post -> {
                    LocalDate postCreatedAt = post.getCreatedAt().toLocalDate();
                    return isWithinThisMonth(postCreatedAt) ? postCreatedAt : null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private boolean isWithinThisMonth(LocalDate postCreatedAt) {
        return postCreatedAt.isAfter(THIS_MONTH_DATE) && postCreatedAt.isBefore(THIS_MONTH_DATE.plusMonths(ONE_MONTH));
    }

    @Transactional(readOnly = true)
    public List<PostResponse> retrievePostInfoByDate(String date, Long userId) {
        return getPostResponses(postRepository.findPostByDateAndUserId(LocalDate.parse(date, DateTimeFormatter.ISO_DATE), userId));
    }

    @NotNull
    private List<PostResponse> getPostResponses(List<Post> posts) {
        return posts.stream()
                .map(PostResponse::from)
                .collect(Collectors.toList());
    }
}
