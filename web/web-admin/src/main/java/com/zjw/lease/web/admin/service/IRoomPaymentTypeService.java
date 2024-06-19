package com.zjw.lease.web.admin.service;

import com.zjw.lease.model.entity.RoomPaymentType;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 房间&支付方式关联表 服务类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
public interface IRoomPaymentTypeService extends IService<RoomPaymentType> {

    /**
     * 根据房间id删除支付方式
     * @param roomInfoId 房间id
     * @return 是否删除成功
     */
    Boolean removeByRoomInfoId(Long roomInfoId);
}
