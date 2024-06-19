package com.zjw.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjw.lease.model.entity.RoomFacility;
import com.zjw.lease.web.admin.mapper.RoomFacilityMapper;
import com.zjw.lease.web.admin.service.IRoomFacilityService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 房间&配套关联表 服务实现类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Service
public class RoomFacilityServiceImpl extends ServiceImpl<RoomFacilityMapper, RoomFacility> implements IRoomFacilityService {

    /**
     * 根据房间id删除房间&配套关联表
     * @param roomInfoId 房间id
     * @return 是否删除成功
     */
    @Override
    public Boolean removeByRoomInfoId(Long roomInfoId) {
        LambdaQueryWrapper<RoomFacility> wrapper = Wrappers.lambdaQuery(RoomFacility.class)
                .eq(RoomFacility::getRoomId, roomInfoId);
        return this.remove(wrapper);
    }

    /**
     * 根据房间id查询配套表id列表
     * @param roomId 房间id
     * @return 配套表id列表
     */
    @Override
    public List<Long> selectFacilityInfoIdListByRoomId(Long roomId) {
        LambdaQueryWrapper<RoomFacility> wrapper = Wrappers.lambdaQuery(RoomFacility.class)
                .eq(RoomFacility::getRoomId, roomId);
        return list(wrapper).stream().map(RoomFacility::getFacilityId).toList();
    }
}
