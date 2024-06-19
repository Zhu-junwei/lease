package com.zjw.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjw.lease.model.entity.PaymentType;
import com.zjw.lease.model.entity.RoomPaymentType;
import com.zjw.lease.web.admin.mapper.PaymentTypeMapper;
import com.zjw.lease.web.admin.service.IPaymentTypeService;
import com.zjw.lease.web.admin.service.IRoomPaymentTypeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 支付方式表 服务实现类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Service
@AllArgsConstructor
public class PaymentTypeServiceImpl extends ServiceImpl<PaymentTypeMapper, PaymentType> implements IPaymentTypeService {

    private final IRoomPaymentTypeService roomPaymentTypeService;

    /**
     * 根据房间id查询支付方式id列表
     * @param roomId 房间id
     * @return 支付方式id列表
     */
    @Override
    public List<PaymentType> selectListByRoomId(Long roomId) {
        LambdaQueryWrapper<RoomPaymentType> wrapper = Wrappers.lambdaQuery(RoomPaymentType.class)
                .eq(RoomPaymentType::getRoomId, roomId);
        List<Long> paymentTypeIdList = roomPaymentTypeService.list(wrapper).stream().map(RoomPaymentType::getPaymentTypeId).toList();
        if (paymentTypeIdList.isEmpty()){
            return Collections.emptyList();
        }
        return this.listByIds(paymentTypeIdList);
    }
}
