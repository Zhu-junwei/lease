package com.zjw.lease.web.admin.service.impl;

import com.zjw.lease.model.entity.ApartmentInfo;
import com.zjw.lease.web.admin.mapper.ApartmentInfoMapper;
import com.zjw.lease.web.admin.mapper.RoomInfoMapper;
import com.zjw.lease.web.admin.service.IApartmentRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 公寓房间中间服务
 *
 * @author 朱俊伟
 * @since 2024/06/12 22:23
 */
@Service
@RequiredArgsConstructor
public class ApartmentRoomServiceImpl implements IApartmentRoomService {

    private final RoomInfoMapper roomInfoMapper;
    private final ApartmentInfoMapper apartmentInfoMapper;

    /**
     * 根据公寓id查询房间信息数量
     * @param apartmentId 公寓id
     * @return 房间信息数量
     */
    @Override
    public Long countRoomByApartmentId(Long apartmentId) {
        return roomInfoMapper.countRoomByApartmentId(apartmentId);
    }


    /**
     * 根据公寓id查询公寓信息
     * @param apartmentId 公寓id
     * @return 公寓信息
     */
    @Override
    public ApartmentInfo getApartmentInfoById(Long apartmentId) {
        return apartmentInfoMapper.selectById(apartmentId);
    }
}
