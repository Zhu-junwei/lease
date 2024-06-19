package com.zjw.lease.web.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjw.lease.model.entity.GraphInfo;
import com.zjw.lease.model.enums.ItemType;
import com.zjw.lease.web.admin.vo.graph.GraphVo;

import java.util.List;

/**
 * <p>
 * 图片信息表 服务类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
public interface IGraphInfoService extends IService<GraphInfo> {

    /**
     * 根据类型和id查询图片信息
     * @param itemType 类型
     * @param itemId itemId
     * @return 图片信息
     */
    List<GraphVo> selectListByItemTypeAndId(ItemType itemType, Long itemId);

    /**
     * 根据公寓id删除图片信息
     * @param apartmentId 公寓id
     * @return 删除结果
     */
    Boolean removeByApartmentId(Long apartmentId);

    /**
     * 根据房间id删除图片信息
     * @param roomInfoId 房间id
     * @return 删除结果
     */
    Boolean removeByRoomInfoId(Long roomInfoId);
}
