package com.zjw.lease.web.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjw.lease.model.entity.PaymentType;

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
     * 根据房间id获取可选支付方式列表
     * @param id 房间id
     * @return 支付方式列表
     */
    List<PaymentType> listByRoomId(Long id);
}
