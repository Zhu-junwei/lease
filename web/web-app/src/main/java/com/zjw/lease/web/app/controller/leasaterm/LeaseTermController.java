package com.zjw.lease.web.app.controller.leasaterm;

import com.zjw.lease.model.entity.LeaseTerm;
import com.zjw.lease.result.Result;
import com.zjw.lease.web.app.service.ILeaseTermService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/app/term/")
@Tag(name = "租期信息")
@RequiredArgsConstructor
public class LeaseTermController {

    private final ILeaseTermService service;

    @GetMapping("listByRoomId")
    @Operation(summary = "根据房间id获取可选获取租期列表")
    public Result<List<LeaseTerm>> list(@RequestParam Long id) {
        List<LeaseTerm> list = service.listByRoomId(id);
        return Result.ok(list);
    }
}
