package com.zjw.lease.web.admin.controller.apartment;


import com.zjw.lease.result.Result;
import com.zjw.lease.model.entity.LeaseTerm;
import com.zjw.lease.web.admin.service.ILeaseTermService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 租期管理
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Tag(name = "租期管理")
@RequestMapping("/admin/term")
@RestController
@AllArgsConstructor
public class LeaseTermController {

    private final ILeaseTermService leaseTermService;

    @GetMapping("list")
    @Operation(summary = "查询全部租期列表")
    public Result<List<LeaseTerm>> listLeaseTerm() {
        return Result.ok(leaseTermService.list());
    }

    @PostMapping("saveOrUpdate")
    @Operation(summary = "保存或更新租期信息")
    public Result saveOrUpdate(@RequestBody LeaseTerm leaseTerm) {
        leaseTermService.saveOrUpdate(leaseTerm);
        return Result.ok();
    }

    @DeleteMapping("deleteById")
    @Operation(summary = "根据ID删除租期")
    public Result deleteLeaseTermById(@RequestParam Long id) {
        leaseTermService.removeById(id);
        return Result.ok();
    }
}
