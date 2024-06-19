package com.zjw.lease.web.app.controller.login;


import com.zjw.lease.web.app.service.ILoginService;
import com.zjw.lease.web.app.vo.user.LoginVo;
import com.zjw.lease.web.app.vo.user.UserInfoVo;
import com.zjw.lease.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 登录管理
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Tag(name = "登录管理")
@RestController
@RequestMapping("/app/")
@RequiredArgsConstructor
public class LoginController {

    private final ILoginService loginService;

    @GetMapping("login/getCode")
    @Operation(summary = "获取短信验证码")
    public Result<String> getCode(@RequestParam String phone) {
        loginService.getSMSCode(phone);
        return Result.ok();
    }

    @PostMapping("login")
    @Operation(summary = "登录")
    public Result<String> login(@RequestBody LoginVo loginVo) {
        String token = loginService.login(loginVo);
        return Result.ok(token);
    }

    @GetMapping("info")
    @Operation(summary = "获取登录用户信息")
    public Result<UserInfoVo> info() {
        UserInfoVo userInfoVo = loginService.login();
        return Result.ok(userInfoVo);
    }
}

