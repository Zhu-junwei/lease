package com.zjw.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjw.lease.model.entity.ApartmentFacility;
import com.zjw.lease.web.admin.mapper.ApartmentFacilityMapper;
import com.zjw.lease.web.admin.service.IApartmentFacilityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 公寓&配套关联表 服务实现类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Service
@AllArgsConstructor
public class ApartmentFacilityServiceImpl extends ServiceImpl<ApartmentFacilityMapper, ApartmentFacility> implements IApartmentFacilityService {

    /**
     * 根据公寓id查询配套id列表
     * @param apartmentId 公寓id
     * @return 配套id列表
     */
    @Override
    public List<Long> selectFacilityInfoIdListByApartmentId(Long apartmentId) {
        LambdaQueryWrapper<ApartmentFacility> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ApartmentFacility::getApartmentId, apartmentId);
        return baseMapper.selectList(wrapper).stream().map(ApartmentFacility::getFacilityId).toList();
    }

    /**
     * 根据公寓id删除图片信息
     * @param apartmentId 公寓id
     * @return 删除结果
     */
    @Override
    public Boolean removeByApartmentId(Long apartmentId) {
        LambdaQueryWrapper<ApartmentFacility> apartmentFacilityQueryWrapper = Wrappers.lambdaQuery(ApartmentFacility.class)
                .eq(ApartmentFacility::getApartmentId, apartmentId);
        return this.remove(apartmentFacilityQueryWrapper);
    }
}
