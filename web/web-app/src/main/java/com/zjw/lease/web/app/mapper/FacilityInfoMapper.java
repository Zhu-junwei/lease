package com.zjw.lease.web.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjw.lease.model.entity.FacilityInfo;

import java.util.List;

/**
 * <p>
 * 配套信息表 Mapper 接口
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
public interface FacilityInfoMapper extends BaseMapper<FacilityInfo> {

    /**
     * 根据房间id查询配套信息
     * @param id 房间id
     * @return 配套信息列表
     */
    List<FacilityInfo> selectListByRoomId(Long id);

    /**
     * 根据公寓id查询配套信息
     * @param apartmentId 公寓id
     * @return 配套信息列表
     */
    List<FacilityInfo> selectListByApartmentId(Long apartmentId);
}
