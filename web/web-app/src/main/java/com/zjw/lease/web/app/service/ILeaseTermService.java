package com.zjw.lease.web.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjw.lease.model.entity.LeaseTerm;

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

    List<LeaseTerm> listByRoomId(Long id);
}
