package com.zjw.lease.web.app.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjw.lease.model.entity.RoomInfo;
import com.zjw.lease.web.app.vo.room.RoomDetailVo;
import com.zjw.lease.web.app.vo.room.RoomItemVo;
import com.zjw.lease.web.app.vo.room.RoomQueryVo;

/**
 * <p>
 * 房间信息表 服务类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
public interface IRoomInfoService extends IService<RoomInfo> {


    /**
     * 分页查询房间列表
     * @param page 分页对象
     * @param queryVo 查询条件
     */
    void pageRoomItemByQuery(Page<RoomItemVo> page, RoomQueryVo queryVo);

    /**
     * 根据id获取房间的详细信息
     * @param id 房间id
     * @return 房间详细信息
     */
    RoomDetailVo getDetailById(Long id);

    /**
     * 分页根据公寓id分页查询房间列表
     * @param page 分页对象
     * @param id 公寓id
     */
    void pageItemByApartmentId(Page<RoomItemVo> page, Long id);
}
