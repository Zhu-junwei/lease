package com.zjw.lease.web.app.controller.apartment;

import com.zjw.lease.result.Result;
import com.zjw.lease.web.app.service.IApartmentInfoService;
import com.zjw.lease.web.app.vo.apartment.ApartmentDetailVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "公寓信息")
@RequestMapping("/app/apartment")
@RequiredArgsConstructor
public class ApartmentController {

    private final IApartmentInfoService service;

    @Operation(summary = "根据id获取公寓信息")
    @GetMapping("getDetailById")
    public Result<ApartmentDetailVo> getDetailById(@RequestParam Long id) {
        ApartmentDetailVo apartmentDetailVo = service.getDetailById(id);
        return Result.ok(apartmentDetailVo);
    }
}
