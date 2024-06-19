package com.zjw.lease.web.admin.controller.apartment;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjw.lease.model.entity.RoomInfo;
import com.zjw.lease.model.enums.ReleaseStatus;
import com.zjw.lease.result.Result;
import com.zjw.lease.web.admin.service.IRoomInfoService;
import com.zjw.lease.web.admin.vo.room.RoomDetailVo;
import com.zjw.lease.web.admin.vo.room.RoomItemVo;
import com.zjw.lease.web.admin.vo.room.RoomQueryVo;
import com.zjw.lease.web.admin.vo.room.RoomSubmitVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 房间信息管理
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Tag(name = "房间信息管理")
@RestController
@RequestMapping("/admin/room")
@AllArgsConstructor
public class RoomController {

    private IRoomInfoService roomInfoService;

    @Operation(summary = "保存或更新房间信息")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody RoomSubmitVo roomSubmitVo) {
        roomInfoService.saveOrUpdateRoomInfo(roomSubmitVo);
        return Result.ok();
    }

    @Operation(summary = "根据条件分页查询房间列表")
    @GetMapping("pageItem")
    public Result<IPage<RoomItemVo>> pageItem(@RequestParam long current,
                                              @RequestParam long size,
                                              RoomQueryVo queryVo) {
        IPage<RoomItemVo> page = new Page<>(current, size);
        IPage<RoomItemVo> list = roomInfoService.pageRoomItemVo(page, queryVo);
        return Result.ok(list);
    }

    @Operation(summary = "根据id获取房间详细信息")
    @GetMapping("getDetailById")
    public Result<RoomDetailVo> getDetailById(@RequestParam("id") Long roomId) {
        RoomDetailVo roomDetailVo = roomInfoService.getDetailById(roomId);
        return Result.ok(roomDetailVo);
    }

    @Operation(summary = "根据id删除房间信息")
    @DeleteMapping("removeById")
    public Result<String> removeById(@RequestParam("id") Long roomId) {
        roomInfoService.removeRoomById(roomId);
        return Result.ok();
    }

    @Operation(summary = "根据id修改房间发布状态")
    @PostMapping("updateReleaseStatusById")
    public Result<String> updateReleaseStatusById(@RequestParam("id") Long roomId, ReleaseStatus status) {
        roomInfoService.updateReleaseStatusById(roomId, status);
        return Result.ok();
    }

    @GetMapping("listBasicByApartmentId")
    @Operation(summary = "根据公寓id查询房间列表")
    public Result<List<RoomInfo>> listBasicByApartmentId(@RequestParam("id") Long apartmentId) {
        List<RoomInfo> roomInfoList = roomInfoService.listBasicByApartmentId(apartmentId);
        return Result.ok(roomInfoList);
    }

}


















