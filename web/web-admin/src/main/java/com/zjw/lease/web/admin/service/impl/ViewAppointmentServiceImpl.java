package com.zjw.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zjw.lease.model.entity.ViewAppointment;
import com.zjw.lease.model.enums.AppointmentStatus;
import com.zjw.lease.web.admin.mapper.ViewAppointmentMapper;
import com.zjw.lease.web.admin.service.IViewAppointmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjw.lease.web.admin.vo.appointment.AppointmentQueryVo;
import com.zjw.lease.web.admin.vo.appointment.AppointmentVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 预约看房信息表 服务实现类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Service
@RequiredArgsConstructor
public class ViewAppointmentServiceImpl extends ServiceImpl<ViewAppointmentMapper, ViewAppointment> implements IViewAppointmentService {

    private final ViewAppointmentMapper viewAppointmentMapper;

    /**
     * 根据id更新预约状态
     * @param id 预约信息id
     * @param status 预约状态
     */
    @Override
    public void updateStatusById(Long id, AppointmentStatus status) {
        LambdaUpdateWrapper<ViewAppointment> wrapper = Wrappers.lambdaUpdate(ViewAppointment.class)
                .set(ViewAppointment::getAppointmentStatus, status)
                .eq(ViewAppointment::getId, id);
        update(wrapper);
    }

    /**
     * 分页查询预约信息
     * @param page 分页对象
     * @param queryVo 查询条件
     * @return 预约信息列表
     */
    @Override
    public IPage<AppointmentVo> pageAppointmentVo(IPage<AppointmentVo> page, AppointmentQueryVo queryVo) {
        return viewAppointmentMapper.pageAppointmentVo(page, queryVo);
    }
}
