package com.zjw.lease.web.admin.mapper;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjw.lease.model.entity.GraphInfo;
import com.zjw.lease.model.enums.ItemType;
import com.zjw.lease.web.admin.vo.graph.GraphVo;

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

    default List<GraphVo> selectListByItemTypeAndId(ItemType itemType, Long itemId) {
        LambdaQueryWrapper<GraphInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(GraphInfo::getItemType, itemType);
        wrapper.eq(GraphInfo::getItemId, itemId);
        List<GraphInfo> graphInfoList = this.selectList(wrapper);
        return BeanUtil.copyToList(graphInfoList, GraphVo.class);
    }
}
