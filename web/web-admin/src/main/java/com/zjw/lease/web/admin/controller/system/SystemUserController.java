package com.zjw.lease.web.admin.controller.system;


import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjw.lease.model.entity.SystemUser;
import com.zjw.lease.model.enums.BaseStatus;
import com.zjw.lease.result.Result;
import com.zjw.lease.web.admin.service.ISystemUserService;
import com.zjw.lease.web.admin.vo.system.user.SystemUserItemVo;
import com.zjw.lease.web.admin.vo.system.user.SystemUserQueryVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 后台用户信息管理
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Tag(name = "后台用户信息管理")
@RestController
@RequestMapping("/admin/system/user")
@RequiredArgsConstructor
public class SystemUserController {

    private final ISystemUserService systemUserService;

    @Operation(summary = "根据条件分页查询后台用户列表")
    @GetMapping("page")
    public Result<IPage<SystemUserItemVo>> page(@RequestParam long current, @RequestParam long size, SystemUserQueryVo queryVo) {
        IPage<SystemUserItemVo> page = new Page<>(current, size);
        systemUserService.selectSystemUserItemVoByPage(page, queryVo);
        return Result.ok(page);
    }

    @Operation(summary = "根据ID查询后台用户信息")
    @GetMapping("getById")
    public Result<SystemUserItemVo> getById(@RequestParam Long id) {
        SystemUserItemVo systemUserItemVo = systemUserService.selectSystemUserItemVoById(id);
        return Result.ok(systemUserItemVo);
    }

    @Operation(summary = "保存或更新后台用户信息")
    @PostMapping("saveOrUpdate")
    public Result<String> saveOrUpdate(@RequestBody SystemUser systemUser) {
        // 新增用户或修改用户密码时需加密
        if (systemUser.getPassword() != null){
            systemUser.setPassword(SecureUtil.md5(systemUser.getPassword()));
        }
        systemUserService.saveOrUpdate(systemUser);
        return Result.ok();
    }

    @Operation(summary = "判断后台用户名是否可用")
    @GetMapping("isUserNameAvailable")
    public Result<Boolean> isUsernameExists(@RequestParam String username) {
        Boolean flag = systemUserService.isUsernameExists(username);
        return Result.ok(flag);
    }

    @DeleteMapping("deleteById")
    @Operation(summary = "根据ID删除后台用户信息")
    public Result<String> removeById(@RequestParam Long id) {
        systemUserService.removeById(id);
        return Result.ok();
    }

    @Operation(summary = "根据ID修改后台用户状态")
    @PostMapping("updateStatusByUserId")
    public Result<String> updateStatusByUserId(@RequestParam Long id, @RequestParam BaseStatus status) {
        systemUserService.updateStatusByUserId(id, status);
        return Result.ok();
    }
}
