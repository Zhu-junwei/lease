package com.zjw.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjw.lease.model.entity.RoomAttrValue;
import com.zjw.lease.web.admin.mapper.RoomAttrValueMapper;
import com.zjw.lease.web.admin.service.IRoomAttrValueService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 房间&基本属性值关联表 服务实现类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Service
public class RoomAttrValueServiceImpl extends ServiceImpl<RoomAttrValueMapper, RoomAttrValue> implements IRoomAttrValueService {

    /**
     * 根据房间id删除房间&基本属性值关联表
     * @param roomInfoId 房间id
     * @return 是否删除成功
     */
    @Override
    public Boolean removeByRoomInfoId(Long roomInfoId) {
        LambdaQueryWrapper<RoomAttrValue> wrapper = Wrappers.lambdaQuery(RoomAttrValue.class)
                .eq(RoomAttrValue::getRoomId, roomInfoId);
        return this.remove(wrapper);
    }

    /**
     * 根据房间id查询属性值id列表
     * @param roomId 房间id
     * @return 属性值id列表
     */
    @Override
    public List<Long> selectAttrValueIdListByRoomId(Long roomId) {
        LambdaQueryWrapper<RoomAttrValue> wrapper = Wrappers.lambdaQuery(RoomAttrValue.class)
                .eq(RoomAttrValue::getRoomId, roomId);
        return baseMapper.selectList(wrapper).stream().map(RoomAttrValue::getAttrValueId).toList();
    }
}
