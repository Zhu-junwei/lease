package com.zjw.lease.web.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjw.lease.model.entity.RoomInfo;
import com.zjw.lease.web.app.vo.room.RoomItemVo;
import com.zjw.lease.web.app.vo.room.RoomQueryVo;

import java.math.BigDecimal;

/**
 * <p>
 * 房间信息表 Mapper 接口
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
public interface RoomInfoMapper extends BaseMapper<RoomInfo> {

    IPage<RoomItemVo> pageRoomItemByQuery(Page<RoomItemVo> page, RoomQueryVo queryVo);

    /**
     * 根据公寓id查询最小租金
     * @param apartmentId 公寓id
     * @return 最小租金
     */
    BigDecimal selectMinRentByApartmentId(Long apartmentId);

    /**
     * 根据公寓id分页查询房间列表
     * @param page 分页对象
     * @param id 公寓id
     */
    IPage<RoomItemVo> pageItemByApartmentId(Page<RoomItemVo> page, Long id);
}
