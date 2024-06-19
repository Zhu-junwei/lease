package com.zjw.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjw.lease.model.entity.ApartmentFeeValue;
import com.zjw.lease.web.admin.mapper.ApartmentFeeValueMapper;
import com.zjw.lease.web.admin.service.IApartmentFeeValueService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 公寓&杂费关联表 服务实现类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Service
@AllArgsConstructor
public class ApartmentFeeValueServiceImpl extends ServiceImpl<ApartmentFeeValueMapper, ApartmentFeeValue> implements IApartmentFeeValueService {

    private final ApartmentFeeValueMapper apartmentFeeValueMapper;

    /**
     * 根据公寓id查询收费项value_id列表
     * @param apartmentId 公寓id
     * @return 收费项value_id列表
     */
    @Override
    public List<Long> selectFeeValueIdsByApartmentId(Long apartmentId) {
        return apartmentFeeValueMapper.selectFeeValueIdsByApartmentId(apartmentId);
    }

    /**
     * 根据公寓id删除公寓收费项关联信息
     * @param apartmentId 公寓id
     * @return 是否删除成功
     */
    @Override
    public Boolean removeByApartmentId(Long apartmentId) {
        LambdaQueryWrapper<ApartmentFeeValue> apartmentFeeValueQueryWrapper = Wrappers.lambdaQuery(ApartmentFeeValue.class)
                .eq(ApartmentFeeValue::getApartmentId, apartmentId);
        return this.remove(apartmentFeeValueQueryWrapper);
    }
}
