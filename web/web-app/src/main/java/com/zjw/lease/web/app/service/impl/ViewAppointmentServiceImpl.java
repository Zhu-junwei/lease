package com.zjw.lease.web.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjw.lease.login.LoginUserHolder;
import com.zjw.lease.model.entity.ViewAppointment;
import com.zjw.lease.web.app.mapper.ViewAppointmentMapper;
import com.zjw.lease.web.app.service.IApartmentInfoService;
import com.zjw.lease.web.app.service.IViewAppointmentService;
import com.zjw.lease.web.app.vo.apartment.ApartmentItemVo;
import com.zjw.lease.web.app.vo.appointment.AppointmentDetailVo;
import com.zjw.lease.web.app.vo.appointment.AppointmentItemVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

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
    private final IApartmentInfoService apartmentInfoService;

    /**
     * 查询个人预约看房列表
     * @return 预约看房列表
     */
    @Override
    public List<AppointmentItemVo> listItemByUserId() {
        Long userId = LoginUserHolder.getLoginUser().getUserId();
        return viewAppointmentMapper.listItemByUserId(userId);
    }

    /**
     * 根据ID查询预约详情信息
     * @param id 预约id
     * @return 预约详情
     */
    @Override
    public AppointmentDetailVo getDetailById(Long id) {
        ViewAppointment viewAppointment = viewAppointmentMapper.selectById(id);

        ApartmentItemVo apartmentItemVo = apartmentInfoService.selectApartmentItemVoById(viewAppointment.getApartmentId());

        AppointmentDetailVo agreementDetailVo = new AppointmentDetailVo();
        BeanUtils.copyProperties(viewAppointment, agreementDetailVo);

        agreementDetailVo.setApartmentItemVo(apartmentItemVo);

        return agreementDetailVo;
    }
}
