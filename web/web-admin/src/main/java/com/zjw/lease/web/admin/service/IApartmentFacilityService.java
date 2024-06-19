package com.zjw.lease.web.admin.service;

import com.zjw.lease.model.entity.ApartmentFacility;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 公寓&配套关联表 服务类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
public interface IApartmentFacilityService extends IService<ApartmentFacility> {

    /**
     * 根据公寓id查询配套id列表
     * @param apartmentId 公寓id
     * @return 配套id列表
     */
    List<Long> selectFacilityInfoIdListByApartmentId(Long apartmentId);

    /**
     * 根据公寓id删除图片信息
     * @param apartmentId 公寓id
     * @return 删除结果
     */
    Boolean removeByApartmentId(Long apartmentId);

}
