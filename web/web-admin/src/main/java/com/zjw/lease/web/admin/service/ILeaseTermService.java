package com.zjw.lease.web.admin.service;

import com.zjw.lease.model.entity.LeaseTerm;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 租期 服务类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
public interface ILeaseTermService extends IService<LeaseTerm> {

    /**
     * 根据房间id查询租期信息
     * @param roomId 房间id
     * @return 租期信息
     */
    List<LeaseTerm> selectListByRoomId(Long roomId);
}
