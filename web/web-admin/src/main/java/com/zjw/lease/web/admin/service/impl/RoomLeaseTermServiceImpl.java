package com.zjw.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjw.lease.model.entity.RoomLeaseTerm;
import com.zjw.lease.web.admin.mapper.RoomLeaseTermMapper;
import com.zjw.lease.web.admin.service.IRoomLeaseTermService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 房间租期管理表 服务实现类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Service
public class RoomLeaseTermServiceImpl extends ServiceImpl<RoomLeaseTermMapper, RoomLeaseTerm> implements IRoomLeaseTermService {

    /**
     * 根据房间id删除租期
     * @param roomInfoId 房间id
     * @return 是否删除成功
     */
    @Override
    public Boolean removeByRoomInfoId(Long roomInfoId) {
        LambdaQueryWrapper<RoomLeaseTerm> wrapper = Wrappers.lambdaQuery(RoomLeaseTerm.class)
                .eq(RoomLeaseTerm::getRoomId, roomInfoId);
        return this.remove(wrapper);    }

    /**
     * 根据房间id查询租期id列表
     * @param roomId 房间id
     * @return 租期id列表
     */
    @Override
    public List<Long> selectLeaseTermIdListByRoomId(Long roomId) {
        LambdaQueryWrapper<RoomLeaseTerm> wrapper = Wrappers.lambdaQuery(RoomLeaseTerm.class)
                .eq(RoomLeaseTerm::getRoomId, roomId);
        return this.list(wrapper).stream().map(RoomLeaseTerm::getLeaseTermId).toList();
    }
}
