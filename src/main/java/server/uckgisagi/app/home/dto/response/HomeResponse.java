package server.uckgisagi.app.home.dto.response;

import lombok.*;
import server.uckgisagi.app.post.dto.response.PostResponse;

import java.time.LocalDate;
import java.util.List;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HomeResponse {

    private UserResponseDto myInfo;
    private List<UserResponseDto> friendInfo;
    private List<LocalDate> postDates;
    private List<PostResponse> myPosts;

    @Builder(access = AccessLevel.PACKAGE)
    private HomeResponse(final UserResponseDto myInfo, final List<UserResponseDto> friendInfo,
                        final List<LocalDate> postDates, final List<PostResponse> myPosts) {
        this.myInfo = myInfo;
        this.friendInfo = friendInfo;
        this.postDates = postDates;
        this.myPosts = myPosts;
    }

    public static HomeResponse of(UserResponseDto myInfo, List<UserResponseDto> friendInfo,
                                  List<LocalDate> postDates, List<PostResponse> myPosts) {
        return HomeResponse.builder()
                .myInfo(myInfo)
                .friendInfo(friendInfo)
                .postDates(postDates)
                .myPosts(myPosts)
                .build();
    }

}
