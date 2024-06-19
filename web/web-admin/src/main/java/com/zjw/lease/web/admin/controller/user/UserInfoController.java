package com.zjw.lease.web.admin.controller.user;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjw.lease.model.entity.UserInfo;
import com.zjw.lease.model.enums.BaseStatus;
import com.zjw.lease.result.Result;
import com.zjw.lease.web.admin.service.IUserInfoService;
import com.zjw.lease.web.admin.vo.user.UserInfoQueryVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户信息管理
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Tag(name = "用户信息管理")
@RestController
@RequestMapping("/admin/user")
@RequiredArgsConstructor
public class UserInfoController {

    private final IUserInfoService userInfoService;

    @Operation(summary = "分页查询用户信息")
    @GetMapping("page")
    public Result<IPage<UserInfo>> pageUserInfo(@RequestParam long current, @RequestParam long size, UserInfoQueryVo queryVo) {
        Page<UserInfo> page = new Page<>(current, size);
        userInfoService.pageUserInfo(page, queryVo);
        return Result.ok(page);
    }

    @Operation(summary = "根据用户id更新账号状态")
    @PostMapping("updateStatusById")
    public Result updateStatusById(@RequestParam Long id, @RequestParam BaseStatus status) {
        userInfoService.updateStatusById(id, status);
        return Result.ok();
    }
}
