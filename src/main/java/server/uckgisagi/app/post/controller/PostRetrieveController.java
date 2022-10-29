package server.uckgisagi.app.post.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import server.uckgisagi.app.post.dto.response.DetailPostResponse;
import server.uckgisagi.app.post.dto.response.PreviewPostResponse;
import server.uckgisagi.app.post.service.PostRetrieveService;
import server.uckgisagi.common.dto.ApiSuccessResponse;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

import static server.uckgisagi.common.success.SuccessResponseResult.*;

@RestController
@RequiredArgsConstructor
public class PostRetrieveController {

    private final PostRetrieveService postRetrieveService;

    @ApiOperation("[인증] 둘러보기 페이지 - 모든 유저의 챌린지 글 조회하기")
    @GetMapping("/v1/post/all")
    public ApiSuccessResponse<List<PreviewPostResponse>> retrieveAllPost(@ApiIgnore Long userId) {
        return ApiSuccessResponse.success(OK_SEARCH_ALL_POST, postRetrieveService.retrieveAllPost(userId));
    }

    @ApiOperation("[인증] 둘러보기 페이지 - 챌린지 글 상세보기")
    @GetMapping("/v1/post/{postId}")
    public ApiSuccessResponse<DetailPostResponse> retrieveDetailPost(@PathVariable Long postId, @ApiIgnore Long userId) {
        return ApiSuccessResponse.success(OK_SEARCH_POST, postRetrieveService.retrieveDetailPost(postId, userId));
    }

    @ApiOperation("[인증] 스크랩 페이지 - 유저가 스크랩한 챌린지 글 조회하기")
    @GetMapping("/v1/post/scrap")
    public ApiSuccessResponse<List<PreviewPostResponse>> retrieveScrapPost(@ApiIgnore Long userId) {
        return ApiSuccessResponse.success(OK_SEARCH_MY_SCRAP_POST, postRetrieveService.retrieveScrapPost(userId));
    }

    @ApiOperation("[인증] 스크랩 페이지 - 유저가 스크랩한 챌린지 글 상세보기")
    @GetMapping("/v1/post/scrap/{postId}")
    public ApiSuccessResponse<DetailPostResponse> retrieveDetailScrapPost(@PathVariable Long postId, @ApiIgnore Long userId) {
        return ApiSuccessResponse.success(OK_SEARCH_MY_SCRAP_POST_DETAIL, postRetrieveService.retrieveDetailScrapPost(postId, userId));
    }

}
