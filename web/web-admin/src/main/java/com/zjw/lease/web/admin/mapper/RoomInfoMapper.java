package com.zjw.lease.web.admin.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zjw.lease.model.entity.RoomInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjw.lease.web.admin.vo.room.RoomItemVo;
import com.zjw.lease.web.admin.vo.room.RoomQueryVo;

/**
 * <p>
 * 房间信息表 Mapper 接口
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
public interface RoomInfoMapper extends BaseMapper<RoomInfo> {

    IPage<RoomItemVo> pageRoomItemByQuery(IPage<RoomItemVo> page, RoomQueryVo queryVo);

    default Long countRoomByApartmentId(Long apartmentId){
        LambdaQueryWrapper<RoomInfo> queryWrapper = Wrappers.lambdaQuery(RoomInfo.class)
                .eq(RoomInfo::getApartmentId, apartmentId);
        return selectCount(queryWrapper);
    }
}
