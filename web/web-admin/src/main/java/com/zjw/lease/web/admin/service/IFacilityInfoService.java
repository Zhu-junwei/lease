package com.zjw.lease.web.admin.service;

import com.zjw.lease.model.entity.FacilityInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 配套信息表 服务类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
public interface IFacilityInfoService extends IService<FacilityInfo> {

    /**
     * 根据房间id查询配套信息
     * @param roomId 房间id
     * @return 配套信息列表
     */
    List<FacilityInfo> selectListByRoomId(Long roomId);
}
