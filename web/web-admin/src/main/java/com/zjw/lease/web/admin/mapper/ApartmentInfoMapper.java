package com.zjw.lease.web.admin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zjw.lease.model.entity.ApartmentInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjw.lease.web.admin.vo.apartment.ApartmentItemVo;
import com.zjw.lease.web.admin.vo.apartment.ApartmentQueryVo;

/**
 * <p>
 * 公寓信息表 Mapper 接口
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
public interface ApartmentInfoMapper extends BaseMapper<ApartmentInfo> {

    IPage<ApartmentItemVo> pageApartmentItemByQuery(IPage<ApartmentItemVo> page, ApartmentQueryVo queryVo);
}
