package com.zjw.lease.web.admin.service;

import com.zjw.lease.model.entity.PaymentType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 支付方式表 服务类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
public interface IPaymentTypeService extends IService<PaymentType> {

    /**
     * 根据房间id查询支付方式id列表
     * @param roomId 房间id
     * @return 支付方式id列表
     */
    List<PaymentType> selectListByRoomId(Long roomId);
}
