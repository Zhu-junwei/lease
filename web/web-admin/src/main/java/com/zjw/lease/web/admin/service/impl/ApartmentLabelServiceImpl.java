package com.zjw.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjw.lease.model.entity.ApartmentLabel;
import com.zjw.lease.web.admin.mapper.ApartmentLabelMapper;
import com.zjw.lease.web.admin.service.IApartmentLabelService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 公寓标签关联表 服务实现类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Service
@AllArgsConstructor
public class ApartmentLabelServiceImpl extends ServiceImpl<ApartmentLabelMapper, ApartmentLabel> implements IApartmentLabelService {

    private final ApartmentLabelMapper apartmentLabelMapper;

    /**
     * 根据公寓id查询标签id列表
     * @param apartmentId 公寓id
     * @return 标签id列表
     */
    @Override
    public List<Long> selectLabelInfoIdListByApartmentId(Long apartmentId) {
        return apartmentLabelMapper.selectLabelInfoIdListByApartmentId(apartmentId);
    }

    /**
     * 根据公寓id删除图片信息
     * @param apartmentId 公寓id
     * @return 删除结果
     */
    @Override
    public Boolean removeByApartmentId(Long apartmentId) {
        LambdaQueryWrapper<ApartmentLabel> apartmentLabelQueryWrapper = Wrappers.lambdaQuery(ApartmentLabel.class)
                .eq(ApartmentLabel::getApartmentId, apartmentId);
        return this.remove(apartmentLabelQueryWrapper);
    }
}
