package com.zjw.lease.web.admin.controller.apartment;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zjw.lease.result.Result;
import com.zjw.lease.model.entity.LabelInfo;
import com.zjw.lease.model.enums.ItemType;
import com.zjw.lease.web.admin.service.ILabelInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 标签管理
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Tag(name = "标签管理")
@RestController
@RequestMapping("/admin/label")
@AllArgsConstructor
public class LabelController {

    private final ILabelInfoService labelService;

    @Operation(summary = "（根据类型）查询标签列表")
    @GetMapping("list")
    public Result<List<LabelInfo>> labelList(@RequestParam(required = false) ItemType type) {
        LambdaQueryWrapper<LabelInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(type != null , LabelInfo::getType, type);
        return Result.ok(labelService.list(lambdaQueryWrapper));
    }

    @Operation(summary = "新增或修改标签信息")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdateLabel(@RequestBody LabelInfo labelInfo) {
        labelService.saveOrUpdate(labelInfo);
        return Result.ok();
    }

    @Operation(summary = "根据id删除标签信息")
    @DeleteMapping("deleteById")
    public Result deleteLabelById(@RequestParam Long id) {
        labelService.removeById(id);
        return Result.ok();
    }
}
