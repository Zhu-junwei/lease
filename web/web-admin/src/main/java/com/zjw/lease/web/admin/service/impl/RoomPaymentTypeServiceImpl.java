package com.zjw.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjw.lease.model.entity.RoomPaymentType;
import com.zjw.lease.web.admin.mapper.RoomPaymentTypeMapper;
import com.zjw.lease.web.admin.service.IRoomPaymentTypeService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 房间&支付方式关联表 服务实现类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Service
public class RoomPaymentTypeServiceImpl extends ServiceImpl<RoomPaymentTypeMapper, RoomPaymentType> implements IRoomPaymentTypeService {

    /**
     * 根据房间id删除支付方式
     * @param roomInfoId 房间id
     * @return 是否删除成功
     */
    @Override
    public Boolean removeByRoomInfoId(Long roomInfoId) {
        LambdaQueryWrapper<RoomPaymentType> wrapper = Wrappers.lambdaQuery(RoomPaymentType.class)
                .eq(RoomPaymentType::getRoomId, roomInfoId);
        return this.remove(wrapper);
    }
}
