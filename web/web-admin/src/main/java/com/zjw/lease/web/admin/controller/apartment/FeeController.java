package com.zjw.lease.web.admin.controller.apartment;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zjw.lease.result.Result;
import com.zjw.lease.model.entity.FeeKey;
import com.zjw.lease.model.entity.FeeValue;
import com.zjw.lease.web.admin.service.IFeeKeyService;
import com.zjw.lease.web.admin.service.IFeeValueService;
import com.zjw.lease.web.admin.vo.fee.FeeKeyVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 房间杂费管理
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Tag(name = "房间杂费管理")
@RestController
@RequestMapping("/admin/fee")
@AllArgsConstructor
public class FeeController {

    private final IFeeKeyService feeKeyService;

    private final IFeeValueService feesValueService;

    @Operation(summary = "保存或更新杂费名称")
    @PostMapping("key/saveOrUpdate")
    public Result saveOrUpdateFeeKey(@RequestBody FeeKey feeKey) {
        feeKeyService.saveOrUpdate(feeKey);
        return Result.ok();
    }

    @Operation(summary = "保存或更新杂费值")
    @PostMapping("value/saveOrUpdate")
    public Result saveOrUpdateFeeValue(@RequestBody FeeValue feeValue) {
        feesValueService.saveOrUpdate(feeValue);
        return Result.ok();
    }


    @Operation(summary = "查询全部杂费名称和杂费值列表")
    @GetMapping("list")
    public Result<List<FeeKeyVo>> feeInfoList() {
        List<FeeKeyVo> feeKeyVoList = feeKeyService.feeInfoList();
        return Result.ok(feeKeyVoList);
    }

    @Operation(summary = "根据id删除杂费名称")
    @DeleteMapping("key/deleteById")
    public Result deleteFeeKeyById(@RequestParam Long feeKeyId) {
        feeKeyService.removeById(feeKeyId);
        // 删除对应值
        LambdaQueryWrapper<FeeValue> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(FeeValue::getFeeKeyId, feeKeyId);
        feesValueService.remove(lambdaQueryWrapper);
        return Result.ok();
    }

    @Operation(summary = "根据id删除杂费值")
    @DeleteMapping("value/deleteById")
    public Result deleteFeeValueById(@RequestParam Long id) {
        feesValueService.removeById(id);
        return Result.ok();
    }
}
