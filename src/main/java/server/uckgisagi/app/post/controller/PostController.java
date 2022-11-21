package server.uckgisagi.app.post.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import server.uckgisagi.app.accusation.dto.AccusePostRequestDto;
import server.uckgisagi.app.accusation.dto.AccusePostResponseDto;
import server.uckgisagi.app.post.dto.request.AddPostRequest;
import server.uckgisagi.app.post.dto.response.GradeResponse;
import server.uckgisagi.app.post.service.PostService;
import server.uckgisagi.common.dto.ApiSuccessResponse;
import server.uckgisagi.config.interceptor.Auth;
import server.uckgisagi.config.resolver.LoginUserId;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

import static server.uckgisagi.common.success.SuccessResponseResult.*;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @ApiOperation("[인증] 챌린지 작성 페이지 - 챌린지 글 작성하기")
    @Auth
    @PostMapping("/v1/post")
    public ApiSuccessResponse<GradeResponse> addPostWithImage(@Valid AddPostRequest request,
                                                              @RequestPart MultipartFile imageFile,
                                                              @ApiIgnore @LoginUserId Long userId) {
        return ApiSuccessResponse.success(CREATED_CERTIFICATION_POST, postService.addPostWithImage(request, imageFile, userId));
    }

    @ApiOperation("[인증] 둘러보기 페이지 - 게시글 신고하기")
    @Auth
    @PostMapping("/v1/post/accuse")
    public ApiSuccessResponse<AccusePostResponseDto> accusePost(@RequestBody AccusePostRequestDto accusePostRequestDto, @ApiIgnore @LoginUserId Long userId) {
        return ApiSuccessResponse.success(CREATED_ACCUSE_POST, postService.accusePost(accusePostRequestDto, userId));
    }
}
