package server.uckgisagi.app.home.service;

import server.uckgisagi.app.home.dto.response.HomePostResponse;
import server.uckgisagi.app.home.dto.response.HomeUserResponse;

/**
 * @see HomeUserResponse
 * @see HomePostResponse
 * @see HomeRetrieveService
 */
public interface HomeService {
    /**
     * <b>홈 상단에 나오는 유저의 정보와 유저의 친구 정보 조회</b>
     * @param userId 유저 아이디
     * @return 유저의 정보와 유저의 친구 정보
     */
    HomeUserResponse retrieveMeAndFriendInfo(Long userId);

    /**
     * <b>홈 컨텐츠 조회</b>
     * @param userId 유저 아이디 혹은 친구의 유저 아이디
     * @return 해당 유저의 이번달 챌린지 글 작성 여부 캘린더 정보와 챌린지 글 정보
     */
    HomePostResponse retrieveHomeContents(Long userId);
}
