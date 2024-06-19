package com.zjw.lease.web.admin.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zjw.lease.model.entity.ApartmentLabel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 公寓标签关联表 Mapper 接口
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
public interface ApartmentLabelMapper extends BaseMapper<ApartmentLabel> {

    /**
     * 根据公寓id查询标签id列表
     * @param apartmentId 公寓id
     * @return 标签id列表
     */
    default List<Long> selectLabelInfoIdListByApartmentId(Long apartmentId){
        LambdaQueryWrapper<ApartmentLabel> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ApartmentLabel::getApartmentId, apartmentId);
        List<ApartmentLabel> apartmentLabelList = selectList(wrapper);
        return apartmentLabelList.stream().map(ApartmentLabel::getLabelId).toList();
    }
}
