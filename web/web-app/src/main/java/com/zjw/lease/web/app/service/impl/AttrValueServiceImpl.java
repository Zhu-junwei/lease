package com.zjw.lease.web.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjw.lease.web.app.mapper.AttrValueMapper;
import com.zjw.lease.web.app.service.IAttrValueService;
import com.zjw.lease.model.entity.AttrValue;
import com.zjw.lease.web.app.mapper.AttrValueMapper;
import com.zjw.lease.web.app.service.IAttrValueService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 房间基本属性值表 服务实现类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Service
public class AttrValueServiceImpl extends ServiceImpl<AttrValueMapper, AttrValue> implements IAttrValueService {

}
