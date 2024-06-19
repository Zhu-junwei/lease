package com.zjw.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjw.lease.model.entity.RoomLabel;
import com.zjw.lease.web.admin.mapper.RoomLabelMapper;
import com.zjw.lease.web.admin.service.IRoomLabelService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 房间&标签关联表 服务实现类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Service
public class RoomLabelServiceImpl extends ServiceImpl<RoomLabelMapper, RoomLabel> implements IRoomLabelService {

    /**
     * 根据房间id删除房间&标签关联表
     * @param roomInfoId 房间id
     * @return 是否删除成功
     */
    @Override
    public Boolean removeByRoomInfoId(Long roomInfoId) {
        LambdaQueryWrapper<RoomLabel> wrapper = Wrappers.lambdaQuery(RoomLabel.class)
                .eq(RoomLabel::getRoomId, roomInfoId);
        return this.remove(wrapper);
    }

    /**
     * 根据房间id查询标签表id列表
     *
     * @param roomId 房间id
     * @return 标签表id列表
     */
    @Override
    public List<Long> selectRoomLabelIdListByRoomId(Long roomId) {
        LambdaQueryWrapper<RoomLabel> wrapper = Wrappers.lambdaQuery(RoomLabel.class)
                .eq(RoomLabel::getRoomId, roomId);
        return list(wrapper).stream().map(RoomLabel::getLabelId).toList();
    }
}
