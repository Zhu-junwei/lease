package com.zjw.lease.web.admin.controller.apartment;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjw.lease.model.entity.ApartmentInfo;
import com.zjw.lease.model.enums.ReleaseStatus;
import com.zjw.lease.result.Result;
import com.zjw.lease.web.admin.service.IApartmentInfoService;
import com.zjw.lease.web.admin.vo.apartment.ApartmentDetailVo;
import com.zjw.lease.web.admin.vo.apartment.ApartmentItemVo;
import com.zjw.lease.web.admin.vo.apartment.ApartmentQueryVo;
import com.zjw.lease.web.admin.vo.apartment.ApartmentSubmitVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * 公寓信息管理
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Tag(name = "公寓信息管理")
@RestController
@RequestMapping("/admin/apartment")
@AllArgsConstructor
public class ApartmentController {

    private IApartmentInfoService apartmentInfoService;

    @Operation(summary = "保存或更新公寓信息")
    @PostMapping("saveOrUpdate")
    public Result<String> saveOrUpdate(@RequestBody ApartmentSubmitVo apartmentSubmitVo) {
        apartmentInfoService.saveOrUpdateApartmentInfo(apartmentSubmitVo);
        return Result.ok();
    }

    @Operation(summary = "根据条件分页查询公寓列表")
    @GetMapping("pageItem")
    public Result<IPage<ApartmentItemVo>> pageItem(@RequestParam long current,
                                                   @RequestParam long size,
                                                   ApartmentQueryVo queryVo) {
        IPage<ApartmentItemVo> page = new Page<>(current, size);
        IPage<ApartmentItemVo> list = apartmentInfoService.pageApartmentItemVo(page, queryVo);
        return Result.ok(list);
    }

    @Operation(summary = "根据ID获取公寓详细信息")
    @GetMapping("getDetailById")
    public Result<ApartmentDetailVo> getDetailById(@RequestParam Long id) {
        ApartmentDetailVo apartmentInfo = apartmentInfoService.getApartmentDetailById(id);
        return Result.ok(apartmentInfo);
    }

    @Operation(summary = "根据id删除公寓信息")
    @DeleteMapping("removeById")
    public Result<String> removeById(@RequestParam("id") Long apartmentId) {
        apartmentInfoService.removeApartmentById(apartmentId);
        return Result.ok();
    }

    @Operation(summary = "根据id修改公寓发布状态")
    @PostMapping("updateReleaseStatusById")
    public Result<String> updateReleaseStatusById(@RequestParam("id") Long apartmentId, @RequestParam ReleaseStatus status) {
        apartmentInfoService.updateReleaseStatusById(apartmentId, status);
        return Result.ok();
    }

    @Operation(summary = "根据区县id查询公寓信息列表")
    @GetMapping("listInfoByDistrictId")
    public Result<List<ApartmentInfo>> listInfoByDistrictId(@RequestParam Long id) {
        List<ApartmentInfo> apartmentInfoList = apartmentInfoService.listInfoByDistrictId(id);
        return Result.ok(apartmentInfoList);
    }
}
