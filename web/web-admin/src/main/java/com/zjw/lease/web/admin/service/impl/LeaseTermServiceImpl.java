package com.zjw.lease.web.admin.service.impl;

import com.zjw.lease.model.entity.LeaseTerm;
import com.zjw.lease.web.admin.mapper.LeaseTermMapper;
import com.zjw.lease.web.admin.service.ILeaseTermService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjw.lease.web.admin.service.IRoomLeaseTermService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 租期 服务实现类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Service
@AllArgsConstructor
public class LeaseTermServiceImpl extends ServiceImpl<LeaseTermMapper, LeaseTerm> implements ILeaseTermService {

    private final IRoomLeaseTermService roomLeaseTermService;

    /**
     * 根据房间id查询租期信息
     * @param roomId 房间id
     * @return 租期信息
     */
    @Override
    public List<LeaseTerm> selectListByRoomId(Long roomId) {
        List<Long> leaseTermIdList = roomLeaseTermService.selectLeaseTermIdListByRoomId(roomId);
        if (leaseTermIdList.isEmpty()){
            return Collections.emptyList();
        }
        return this.listByIds(leaseTermIdList);
    }
}
