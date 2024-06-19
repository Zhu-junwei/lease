package com.zjw.lease.web.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zjw.lease.model.entity.ApartmentInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjw.lease.model.enums.ReleaseStatus;
import com.zjw.lease.web.admin.vo.apartment.ApartmentDetailVo;
import com.zjw.lease.web.admin.vo.apartment.ApartmentItemVo;
import com.zjw.lease.web.admin.vo.apartment.ApartmentQueryVo;
import com.zjw.lease.web.admin.vo.apartment.ApartmentSubmitVo;

import java.util.List;

/**
 * <p>
 * 公寓信息表 服务类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
public interface IApartmentInfoService extends IService<ApartmentInfo> {

    /**
     * 保存或更新公寓信息
     */
    void saveOrUpdateApartmentInfo(ApartmentSubmitVo apartmentSubmitVo);

    /**
     * 根据条件分页查询公寓列表
     */
    IPage<ApartmentItemVo> pageApartmentItemVo(IPage<ApartmentItemVo> page, ApartmentQueryVo queryVo);

    /**
     * 根据id获取公寓详细信息
     * @param apartmentId 公寓id
     */
    ApartmentDetailVo getApartmentDetailById(Long apartmentId);

    /**
     * 根据id删除公寓信息
     * @param apartmentId 公寓id
     */
    void removeApartmentById(Long apartmentId);

    /**
     * 修改公寓发布状态
     * @param apartmentId 公寓id
     * @param status 发布状态
     */
    void updateReleaseStatusById(Long apartmentId, ReleaseStatus status);

    /**
     * 根据区县id查询公寓信息列表
     * @param districtId 区县id
     * @return 公寓信息列表
     */
    List<ApartmentInfo> listInfoByDistrictId(Long districtId);
}
