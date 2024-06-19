package com.zjw.lease.web.app.mapper;

import com.zjw.lease.model.entity.PaymentType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 支付方式表 Mapper 接口
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
public interface PaymentTypeMapper extends BaseMapper<PaymentType> {

    /**
     * 根据房间id查询支付方式列表
     * @param id 房间id
     * @return 支付方式列表
     */
    List<PaymentType> selectListByRoomId(Long id);
}
