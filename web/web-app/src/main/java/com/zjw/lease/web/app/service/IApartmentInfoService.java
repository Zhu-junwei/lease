package com.zjw.lease.web.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjw.lease.model.entity.ApartmentInfo;
import com.zjw.lease.web.app.vo.apartment.ApartmentDetailVo;
import com.zjw.lease.web.app.vo.apartment.ApartmentItemVo;

/**
 * <p>
 * 公寓信息表 服务类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
public interface IApartmentInfoService extends IService<ApartmentInfo> {

    /**
     * 根据id获取公寓信息
     * @param apartmentId 公寓id
     * @return 公寓信息
     */
    ApartmentItemVo selectApartmentItemVoById(Long apartmentId);

    /**
     * 根据id获取公寓详细信息
     * @param apartmentId 公寓id
     * @return 公寓详细信息
     */
    ApartmentDetailVo getDetailById(Long apartmentId);
}
