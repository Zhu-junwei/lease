package com.zjw.lease.web.admin.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjw.lease.model.entity.ApartmentFeeValue;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 公寓&杂费关联表 Mapper 接口
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
public interface ApartmentFeeValueMapper extends BaseMapper<ApartmentFeeValue> {

    /**
     * 根据公寓id查询收费项value_id列表
     * @param apartmentId 公寓id
     * @return 收费项value_id列表
     */
    default List<Long> selectFeeValueIdsByApartmentId(Long apartmentId) {
        LambdaQueryWrapper<ApartmentFeeValue> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ApartmentFeeValue::getApartmentId, apartmentId);

        return selectList(wrapper)
                .stream()
                .map(ApartmentFeeValue::getFeeValueId)
                .collect(Collectors.toList());
    }
}
