package com.zjw.lease.web.admin.service;

import com.zjw.lease.model.entity.ApartmentFeeValue;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 公寓&杂费关联表 服务类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
public interface IApartmentFeeValueService extends IService<ApartmentFeeValue> {

    /**
     * 根据公寓id查询收费项value_id列表
     * @param apartmentId 公寓id
     * @return 收费项value_id列表
     */
    List<Long> selectFeeValueIdsByApartmentId(Long apartmentId);

    /**
     * 根据公寓id删除公寓收费项关联信息
     * @param apartmentId 公寓id
     * @return 是否删除成功
     */
    Boolean removeByApartmentId(Long apartmentId);
}
