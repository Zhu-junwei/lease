package com.zjw.lease.web.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zjw.lease.model.entity.RoomInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjw.lease.model.enums.ReleaseStatus;
import com.zjw.lease.web.admin.vo.room.RoomDetailVo;
import com.zjw.lease.web.admin.vo.room.RoomItemVo;
import com.zjw.lease.web.admin.vo.room.RoomQueryVo;
import com.zjw.lease.web.admin.vo.room.RoomSubmitVo;

import java.util.List;

/**
 * <p>
 * 房间信息表 服务类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
public interface IRoomInfoService extends IService<RoomInfo> {

    /**
     * 保存或更新房间信息
     * @param roomSubmitVo 房间信息
     */
    void saveOrUpdateRoomInfo(RoomSubmitVo roomSubmitVo);

    /**
     * 根据id删除房间信息
     * @param roomId 房间id
     */
    void removeRoomById(Long roomId);

    /**
     * 根据id修改房间发布状态
     * @param roomId 房间id
     * @param status 发布状态
     */
    void updateReleaseStatusById(Long roomId, ReleaseStatus status);

    /**
     * 根据公寓id查询房间列表
     * @param apartmentId 公寓id
     * @return 房间列表
     */
    List<RoomInfo> listBasicByApartmentId(Long apartmentId);

    /**
     * 根据条件分页查询房间列表
     * @param page 分页对象
     * @param queryVo 查询条件
     * @return 房间列表
     */
    IPage<RoomItemVo> pageRoomItemVo(IPage<RoomItemVo> page, RoomQueryVo queryVo);

    /**
     * 根据id获取房间详细信息
     * @param roomId 房间id
     * @return 房间详细信息
     */
    RoomDetailVo getDetailById(Long roomId);
}
