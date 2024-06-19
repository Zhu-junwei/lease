package com.zjw.lease.web.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjw.lease.model.entity.ViewAppointment;
import com.zjw.lease.web.app.vo.appointment.AppointmentDetailVo;
import com.zjw.lease.web.app.vo.appointment.AppointmentItemVo;

import java.util.List;

/**
 * <p>
 * 预约看房信息表 服务类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
public interface IViewAppointmentService extends IService<ViewAppointment> {

    /**
     * 查询个人预约看房列表
     * @return 预约看房列表
     */
    List<AppointmentItemVo> listItemByUserId();

    /**
     * 根据ID查询预约详情信息
     * @param id 预约id
     * @return 预约详情
     */
    AppointmentDetailVo getDetailById(Long id);
}
