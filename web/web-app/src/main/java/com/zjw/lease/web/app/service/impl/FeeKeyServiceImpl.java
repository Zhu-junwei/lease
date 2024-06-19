package com.zjw.lease.web.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjw.lease.web.app.mapper.FeeKeyMapper;
import com.zjw.lease.web.app.service.IFeeKeyService;
import com.zjw.lease.model.entity.FeeKey;
import com.zjw.lease.web.app.mapper.FeeKeyMapper;
import com.zjw.lease.web.app.service.IFeeKeyService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 杂项费用名称表 服务实现类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Service
public class FeeKeyServiceImpl extends ServiceImpl<FeeKeyMapper, FeeKey> implements IFeeKeyService {

}
