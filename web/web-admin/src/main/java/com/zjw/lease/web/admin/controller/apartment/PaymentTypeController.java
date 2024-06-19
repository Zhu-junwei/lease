package com.zjw.lease.web.admin.controller.apartment;


import com.zjw.lease.result.Result;
import com.zjw.lease.model.entity.PaymentType;
import com.zjw.lease.web.admin.service.IPaymentTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 支付方式管理
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Tag(name = "支付方式管理")
@RequestMapping("/admin/payment")
@RestController
@AllArgsConstructor
public class PaymentTypeController {

    private final IPaymentTypeService paymentTypeService;

    @Operation(summary = "查询全部支付方式列表")
    @GetMapping("list")
    public Result<List<PaymentType>> listPaymentType() {
        return Result.ok(paymentTypeService.list());
    }

    @Operation(summary = "保存或更新支付方式")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdatePaymentType(@RequestBody PaymentType paymentType) {
        paymentTypeService.saveOrUpdate(paymentType);
        return Result.ok();
    }

    @Operation(summary = "根据ID删除支付方式")
    @DeleteMapping("deleteById")
    public Result deletePaymentById(@RequestParam Long id) {
        paymentTypeService.removeById(id);
        return Result.ok();
    }

}















