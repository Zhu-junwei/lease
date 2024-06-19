package com.zjw.lease.web.admin.service;

import com.zjw.lease.model.entity.RoomLeaseTerm;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 房间租期管理表 服务类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
public interface IRoomLeaseTermService extends IService<RoomLeaseTerm> {

    /**
     * 根据房间id删除租期
     * @param roomInfoId 房间id
     * @return 是否删除成功
     */
    Boolean removeByRoomInfoId(Long roomInfoId);

    /**
     * 根据房间id查询租期id列表
     * @param roomId 房间id
     * @return 租期id列表
     */
    List<Long> selectLeaseTermIdListByRoomId(Long roomId);
}
