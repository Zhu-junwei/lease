package com.zjw.lease.web.admin.service;

import com.zjw.lease.model.entity.ApartmentInfo;

/**
 * 公寓房间中间服务
 *
 * @author 朱俊伟
 * @since 2024/06/12 22:23
 */
public interface IApartmentRoomService {

    /**
     * 根据公寓id查询房间信息数量
     * @param apartmentId 公寓id
     * @return 房间信息数量
     */
    Long countRoomByApartmentId(Long apartmentId);

    /**
     * 根据公寓id查询公寓信息
     * @param apartmentId 公寓id
     * @return 公寓信息
     */
    ApartmentInfo getApartmentInfoById(Long apartmentId);
}
