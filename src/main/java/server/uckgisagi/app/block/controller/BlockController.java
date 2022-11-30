package server.uckgisagi.app.block.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import server.uckgisagi.common.dto.ApiSuccessResponse;
import server.uckgisagi.common.success.SuccessResponseResult;
import server.uckgisagi.config.interceptor.Auth;
import server.uckgisagi.config.resolver.LoginUserId;
import springfox.documentation.annotations.ApiIgnore;

import static server.uckgisagi.common.success.SuccessResponseResult.NO_CONTENT_BLOCK_USER;

@RestController
@RequiredArgsConstructor
public class BlockController {

    private final BlockService blockService;

    @ApiOperation("[인증] 유저 차단 페이지 - 유저 차단하기")
    @Auth
    @PostMapping("/v1/block")
    public ApiSuccessResponse<SuccessResponseResult> blockUser(@RequestBody BlockUserDto blockUserRequestDto, @ApiIgnore @LoginUserId Long userId) {
        blockService.blockUser(blockUserRequestDto, userId);
        return ApiSuccessResponse.success(NO_CONTENT_BLOCK_USER);
    }

}
