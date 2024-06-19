package com.zjw.lease.web.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjw.lease.model.entity.PaymentType;
import com.zjw.lease.web.app.mapper.PaymentTypeMapper;
import com.zjw.lease.web.app.service.IPaymentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
@RequiredArgsConstructor
public class PaymentTypeServiceImpl extends ServiceImpl<PaymentTypeMapper, PaymentType> implements IPaymentTypeService {

    private final PaymentTypeMapper paymentTypeMapper;

    /**
     * 根据房间id获取可选支付方式列表
     * @param id 房间id
     * @return 支付方式列表
     */
    @Override
    public List<PaymentType> listByRoomId(Long id) {
        return paymentTypeMapper.selectListByRoomId(id);
    }
}
