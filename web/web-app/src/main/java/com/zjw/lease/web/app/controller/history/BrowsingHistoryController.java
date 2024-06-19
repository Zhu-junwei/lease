package com.zjw.lease.web.app.controller.history;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjw.lease.login.LoginUser;
import com.zjw.lease.login.LoginUserHolder;
import com.zjw.lease.result.Result;
import com.zjw.lease.web.app.service.IBrowsingHistoryService;
import com.zjw.lease.web.app.vo.history.HistoryItemVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "浏览历史管理")
@RequestMapping("/app/history")
@RequiredArgsConstructor
public class BrowsingHistoryController {

    private static final Logger log = LoggerFactory.getLogger(BrowsingHistoryController.class);
    private final IBrowsingHistoryService browsingHistoryService;

    @Operation(summary = "获取浏览历史")
    @GetMapping("pageItem")
    private Result<IPage<HistoryItemVo>> page(@RequestParam long current, @RequestParam long size) {
        IPage<HistoryItemVo> page = new Page<>(current, size);
        LoginUser loginUser = LoginUserHolder.getLoginUser();
        Long userId = loginUser.getUserId();
        browsingHistoryService.pageHistoryItemVo(page, userId);
        return Result.ok(page);
    }
}
