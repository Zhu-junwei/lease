package com.zjw.lease.web.admin.service;

import com.zjw.lease.model.entity.ApartmentLabel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 公寓标签关联表 服务类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
public interface IApartmentLabelService extends IService<ApartmentLabel> {

    /**
     * 根据公寓id查询标签id列表
     * @param apartmentId 公寓id
     * @return 标签id列表
     */
    List<Long> selectLabelInfoIdListByApartmentId(Long apartmentId);

    /**
     * 根据公寓id删除图片信息
     * @param apartmentId 公寓id
     * @return 删除结果
     */
    Boolean removeByApartmentId(Long apartmentId);
}
