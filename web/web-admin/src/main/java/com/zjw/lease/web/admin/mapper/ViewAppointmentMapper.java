package com.zjw.lease.web.admin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zjw.lease.model.entity.ViewAppointment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjw.lease.web.admin.vo.appointment.AppointmentQueryVo;
import com.zjw.lease.web.admin.vo.appointment.AppointmentVo;

/**
 * <p>
 * 预约看房信息表 Mapper 接口
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
public interface ViewAppointmentMapper extends BaseMapper<ViewAppointment> {

    IPage<AppointmentVo> pageAppointmentVo(IPage<AppointmentVo> page, AppointmentQueryVo queryVo);
}
