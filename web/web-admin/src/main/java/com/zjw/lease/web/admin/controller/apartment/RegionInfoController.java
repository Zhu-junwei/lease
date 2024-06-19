package com.zjw.lease.web.admin.controller.apartment;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zjw.lease.result.Result;
import com.zjw.lease.model.entity.CityInfo;
import com.zjw.lease.model.entity.DistrictInfo;
import com.zjw.lease.model.entity.ProvinceInfo;
import com.zjw.lease.web.admin.service.ICityInfoService;
import com.zjw.lease.web.admin.service.IDistrictInfoService;
import com.zjw.lease.web.admin.service.IProvinceInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 地区信息管理
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Tag(name = "地区信息管理")
@RestController
@RequestMapping("/admin/region")
@AllArgsConstructor
public class RegionInfoController {

    private final IProvinceInfoService provinceInfoService;

    private final ICityInfoService cityInfoService;

    private final IDistrictInfoService discoverInfoService;

    @Operation(summary = "查询省份信息列表")
    @GetMapping("province/list")
    public Result<List<ProvinceInfo>> listProvince() {
        return Result.ok(provinceInfoService.list());
    }

    @Operation(summary = "根据省份id查询城市信息列表")
    @GetMapping("city/listByProvinceId")
    public Result<List<CityInfo>> listCityInfoByProvinceId(@RequestParam Long id) {
        LambdaQueryWrapper<CityInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CityInfo::getProvinceId, id);
        List<CityInfo> cityInfoList = cityInfoService.list(wrapper);
        return Result.ok(cityInfoList);
    }

    @GetMapping("district/listByCityId")
    @Operation(summary = "根据城市id查询区县信息")
    public Result<List<DistrictInfo>> listDistrictInfoByCityId(@RequestParam Long id) {
        LambdaQueryWrapper<DistrictInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DistrictInfo::getCityId, id);
        List<DistrictInfo> districtInfoList = discoverInfoService.list(wrapper);
        return Result.ok(districtInfoList);
    }

}
