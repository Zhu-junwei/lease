package com.zjw.lease.web.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjw.lease.model.entity.ViewAppointment;
import com.zjw.lease.web.app.vo.appointment.AppointmentItemVo;

import java.util.List;

/**
 * <p>
 * 预约看房信息表 Mapper 接口
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
public interface ViewAppointmentMapper extends BaseMapper<ViewAppointment> {

    /**
     * 查询个人预约看房列表
     * @param userId 用户id
     * @return 预约看房列表
     */
    List<AppointmentItemVo> listItemByUserId(Long userId);
}
