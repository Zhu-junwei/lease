package com.zjw.lease.web.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjw.lease.web.app.mapper.FeeValueMapper;
import com.zjw.lease.model.entity.FeeValue;
import com.zjw.lease.web.app.mapper.FeeValueMapper;
import com.zjw.lease.web.app.service.IFeeValueService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 杂项费用值表 服务实现类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Service
public class FeeValueServiceImpl extends ServiceImpl<FeeValueMapper, FeeValue> implements IFeeValueService {

}
