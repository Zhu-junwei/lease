package com.zjw.lease.web.app.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zjw.lease.model.entity.GraphInfo;
import com.zjw.lease.model.enums.ItemType;
import com.zjw.lease.web.app.vo.graph.GraphVo;

import java.util.List;

/**
 * <p>
 * 图片信息表 Mapper 接口
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
public interface GraphInfoMapper extends BaseMapper<GraphInfo> {

    /**
     * 根据类型和id查询图片列表
     * @param itemType 图片类型
     * @param id 公寓或房间id
     * @return 图片列表
     */
    default List<GraphVo> selectListByItemTypeAndId(ItemType itemType, Long id){
        LambdaQueryWrapper<GraphInfo> wrapper = Wrappers.lambdaQuery(GraphInfo.class)
                .eq(GraphInfo::getItemType, itemType)
                .eq(GraphInfo::getItemId, id);
        return selectList(wrapper).stream().map(graphInfo -> {
            GraphVo graphVo = new GraphVo();
            graphVo.setName(graphInfo.getName());
            graphVo.setUrl(graphInfo.getUrl());
            return graphVo;
        }).toList();
    }
}
