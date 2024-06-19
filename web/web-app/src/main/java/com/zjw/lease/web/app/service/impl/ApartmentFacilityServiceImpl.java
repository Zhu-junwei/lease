package com.zjw.lease.web.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjw.lease.web.app.mapper.ApartmentFacilityMapper;
import com.zjw.lease.web.app.service.IApartmentFacilityService;
import com.zjw.lease.model.entity.ApartmentFacility;
import com.zjw.lease.web.app.mapper.ApartmentFacilityMapper;
import com.zjw.lease.web.app.service.IApartmentFacilityService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 公寓&配套关联表 服务实现类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Service
public class ApartmentFacilityServiceImpl extends ServiceImpl<ApartmentFacilityMapper, ApartmentFacility> implements IApartmentFacilityService {

}
