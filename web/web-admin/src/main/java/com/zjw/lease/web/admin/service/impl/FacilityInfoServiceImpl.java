package com.zjw.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjw.lease.model.entity.FacilityInfo;
import com.zjw.lease.web.admin.mapper.FacilityInfoMapper;
import com.zjw.lease.web.admin.service.IFacilityInfoService;
import com.zjw.lease.web.admin.service.IRoomFacilityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 配套信息表 服务实现类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Service
@AllArgsConstructor
public class FacilityInfoServiceImpl extends ServiceImpl<FacilityInfoMapper, FacilityInfo> implements IFacilityInfoService {

    private final IRoomFacilityService roomFacilityService;

    /**
     * 根据房间id查询配套信息
     *
     * @param roomId 房间id
     * @return 配套信息列表
     */
    @Override
    public List<FacilityInfo> selectListByRoomId(Long roomId) {
        List<Long> facilityInfoIdList = roomFacilityService.selectFacilityInfoIdListByRoomId(roomId);
        if (facilityInfoIdList.isEmpty()) {
            return Collections.emptyList();
        }
        return this.listByIds(facilityInfoIdList);
    }
}
