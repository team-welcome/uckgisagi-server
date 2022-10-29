package server.uckgisagi.common.success;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static server.uckgisagi.common.success.SuccessStatusCode.*;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessResponseResult {

    // 200 OK
    SUCCESS_OK(OK, ""),
    OK_RETRIEVE_USER_INFO(OK, "유저의 정보를 조회했습니다"),
    OK_SEARCH_USER(OK, "닉네임으로 유저를 검색했습니다"),
    OK_RETRIEVE_ONE_STORE(OK, "리필 스테이션을 조회했습니다"),
    OK_RETRIEVE_ALL_STORE(OK, "모든 리필 스테이션을 조회했습니다"),
    OK_RETRIEVE_STORE_REVIEW(OK, "리필 스테이션의 후기를 조회했습니다"),
    OK_SEARCH_STORE(OK, "리필 스테이션의 위치를 조회했습니다"),
    OK_SEARCH_POST(OK, "챌린지 글을 조회했습니다"),
    OK_SEARCH_MY_SCRAP_POST(OK, "스크랩한 챌린지 글을 모두 조회했습니다"),
    OK_SEARCH_MY_SCRAP_POST_DETAIL(OK, "스크랩한 챌린지 글을 조회했습니다"),
    OK_SEARCH_ALL_POST(OK, "모든 챌린지 글을 조회했습니다"),
    OK_SEARCH_MY_HOME_CONTENTS(OK, "나의 홈 뷰를 조회했습니다"),
    OK_SEARCH_FRIEND_HOME_CONTENTS(OK, "친구의 홈 뷰를 조회했습니다"),

    // 201 CREATED
    SUCCESS_CREATED(CREATED, ""),
    CREATED_REISSUE_TOKEN(CREATED, "토큰이 성공적으로 재발급 되었습니다"),
    CREATED_REVIEW_COMMENT(CREATED, "후기가 등록되었습니다"),
    CREATED_UPDATE_NICKNAME(CREATED, "닉네임이 수정되었습니다"),
    CREATED_STORE(CREATED, "새로운 리필 스테이션이 등록되었습니다"),
    CREATED_CERTIFICATION_POST(CREATED, "새로운 인증 포스트가 등록되었습니다"),
    CREATED_UPDATE_STORE(CREATED, "리필 스테이션 정보가 수정되었습니다"),
    CREATED_NOTIFICATION(CREATED, "알람이 성공적으로 전송되었습니다"),

    // 202 ACCEPTED
    SUCCESS_ACCEPTED(ACCEPTED, ""),

    // 204 NOT_CONTENT
    SUCCESS_NO_CONTENT(NO_CONTENT, ""),
    NO_CONTENT_UNFOLLOW_USER(NO_CONTENT, "언팔로우에 성공했습니다"),
    NO_CONTENT_SCRAP_POST(NO_CONTENT, "챌린지 글 스크랩에 성공했습니다"),
    NO_CONTENT_CANCEL_SCRAP_POST(NO_CONTENT, "챌린지 글 스크랩 취소에 성공했습니다"),
    ;

    private final SuccessStatusCode statusCode;
    private final String message;

}
