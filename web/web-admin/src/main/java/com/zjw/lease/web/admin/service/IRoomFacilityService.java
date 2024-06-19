package com.zjw.lease.web.admin.service;

import com.zjw.lease.model.entity.RoomFacility;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * <p>
 * 房间&配套关联表 服务类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
public interface IRoomFacilityService extends IService<RoomFacility> {

    /**
     * 根据房间id删除房间&配套关联表
     *
     * @param roomInfoId 房间id
     * @return 是否删除成功
     */
    Boolean removeByRoomInfoId(Long roomInfoId);

    /**
     * 根据房间id查询配套表id列表
     *
     * @param roomId 房间id
     * @return 配套表id列表
     */
    List<Long> selectFacilityInfoIdListByRoomId(Long roomId);
}