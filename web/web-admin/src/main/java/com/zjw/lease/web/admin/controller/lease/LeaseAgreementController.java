package com.zjw.lease.web.admin.controller.lease;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjw.lease.model.entity.LeaseAgreement;
import com.zjw.lease.model.enums.LeaseStatus;
import com.zjw.lease.result.Result;
import com.zjw.lease.web.admin.service.ILeaseAgreementService;
import com.zjw.lease.web.admin.vo.agreement.AgreementQueryVo;
import com.zjw.lease.web.admin.vo.agreement.AgreementVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 租约管理
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Tag(name = "租约管理")
@RestController
@RequestMapping("/admin/agreement")
@RequiredArgsConstructor
public class LeaseAgreementController {

    private final ILeaseAgreementService leaseAgreementService;

    @Operation(summary = "保存或修改租约信息")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody LeaseAgreement leaseAgreement) {
        leaseAgreementService.saveOrUpdate(leaseAgreement);
        return Result.ok();
    }

    @Operation(summary = "根据条件分页查询租约列表")
    @GetMapping("page")
    public Result<IPage<AgreementVo>> page(@RequestParam long current, @RequestParam long size, AgreementQueryVo queryVo) {
        IPage<AgreementVo> page = new Page<>(current, size);
        leaseAgreementService.pageAgreementByQuery(page, queryVo);
        return Result.ok(page);
    }

    @Operation(summary = "根据id查询租约信息")
    @GetMapping(name = "getById")
    public Result<AgreementVo> getById(@RequestParam Long id) {
        AgreementVo agreementVo = leaseAgreementService.getAgreementVoById(id);
        return Result.ok(agreementVo);
    }

    @Operation(summary = "根据id删除租约信息")
    @DeleteMapping("removeById")
    public Result removeById(@RequestParam Long id) {
        leaseAgreementService.removeById(id);
        return Result.ok();
    }

    @Operation(summary = "根据id更新租约状态")
    @PostMapping("updateStatusById")
    public Result updateStatusById(@RequestParam Long id, @RequestParam LeaseStatus status) {
        leaseAgreementService.updateStatusById(id, status);
        return Result.ok();
    }

}

