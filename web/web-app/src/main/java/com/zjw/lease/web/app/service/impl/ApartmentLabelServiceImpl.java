package com.zjw.lease.web.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjw.lease.web.app.mapper.ApartmentLabelMapper;
import com.zjw.lease.web.app.service.IApartmentLabelService;
import com.zjw.lease.model.entity.ApartmentLabel;
import com.zjw.lease.web.app.mapper.ApartmentLabelMapper;
import com.zjw.lease.web.app.service.IApartmentLabelService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 公寓标签关联表 服务实现类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Service
public class ApartmentLabelServiceImpl extends ServiceImpl<ApartmentLabelMapper, ApartmentLabel> implements IApartmentLabelService {

}
