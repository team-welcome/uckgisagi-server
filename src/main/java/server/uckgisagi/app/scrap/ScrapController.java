package server.uckgisagi.app.scrap;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import server.uckgisagi.app.scrap.dto.request.ScrapRequest;
import server.uckgisagi.app.scrap.service.ScrapService;
import server.uckgisagi.common.dto.ApiSuccessResponse;
import server.uckgisagi.common.success.SuccessResponseResult;
import server.uckgisagi.config.interceptor.Auth;
import server.uckgisagi.config.resolver.LoginUserId;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

import static server.uckgisagi.common.success.SuccessResponseResult.*;

@RestController
@RequiredArgsConstructor
public class ScrapController {

    private final ScrapService scrapService;

    @ApiOperation("[인증] 스크랩이 가능한 모든 페이지 - 스크랩 하기")
    @Auth
    @PostMapping("/v1/scrap")
    public ApiSuccessResponse<SuccessResponseResult> addScrap(@Valid @RequestBody ScrapRequest request, @ApiIgnore @LoginUserId Long userId) {
        scrapService.addScrap(request, userId);
        return ApiSuccessResponse.success(NO_CONTENT_SCRAP_POST);
    }

    @ApiOperation("[인증] 스크랩이 가능한 모든 페이지 - 스크랩 취소 하기")
    @Auth
    @DeleteMapping("/v1/scrap/{postId}")
    public ApiSuccessResponse<SuccessResponseResult> deleteScrap(@PathVariable Long postId, @ApiIgnore @LoginUserId Long userId) {
        scrapService.deleteScrap(postId, userId);
        return ApiSuccessResponse.success(NO_CONTENT_CANCEL_SCRAP_POST);
    }

}
