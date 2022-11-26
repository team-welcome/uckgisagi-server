package server.uckgisagi.app.accusation.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import server.uckgisagi.app.accusation.dto.AccusationPostReqDto;
import server.uckgisagi.app.accusation.dto.AccusationPostResDto;
import server.uckgisagi.app.accusation.service.AccusationService;
import server.uckgisagi.common.dto.ApiSuccessResponse;
import server.uckgisagi.config.interceptor.Auth;
import server.uckgisagi.config.resolver.LoginUserId;
import springfox.documentation.annotations.ApiIgnore;

import static server.uckgisagi.common.success.SuccessResponseResult.CREATED_ACCUSE_POST;

@RestController
@RequiredArgsConstructor
public class AccusationController {

    private final AccusationService accusationService;

    @ApiOperation("[인증] 둘러보기 페이지 - 게시글 신고하기")
    @Auth
    @PostMapping("/v1/post/accuse")
    public ApiSuccessResponse<AccusationPostResDto> accusePost(@RequestBody AccusationPostReqDto accusationPostReqDto, @ApiIgnore @LoginUserId Long userId) {
        return ApiSuccessResponse.success(CREATED_ACCUSE_POST, accusationService.accusePost(accusationPostReqDto, userId));
    }
}
