package server.uckgisagi.app.post.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import server.uckgisagi.app.post.dto.request.AddPostRequest;
import server.uckgisagi.app.post.dto.response.GradeResponse;
import server.uckgisagi.app.post.service.PostService;
import server.uckgisagi.common.dto.ApiSuccessResponse;
import server.uckgisagi.common.success.SuccessResponseResult;
import server.uckgisagi.config.interceptor.Auth;
import server.uckgisagi.config.resolver.LoginUserId;
import server.uckgisagi.domain.post.entity.Post;
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

    @ApiOperation("[인증] 챌린지 글 삭제 페이지 - 챌린지 글 삭제하기")
    @Auth
    @DeleteMapping("/v1/post/delete")
    public ApiSuccessResponse<SuccessResponseResult> deletePost(@RequestParam Long postId, @ApiIgnore @LoginUserId Long userId) {
        postService.deletePost(postId, userId);
        return ApiSuccessResponse.success(NO_CONTENT_DELETE_POST);
    }

}
