package com.zjw.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjw.lease.model.entity.AttrValue;
import com.zjw.lease.web.admin.mapper.AttrValueMapper;
import com.zjw.lease.web.admin.service.IAttrValueService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 房间基本属性值表 服务实现类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Service
@AllArgsConstructor
public class AttrValueServiceImpl extends ServiceImpl<AttrValueMapper, AttrValue> implements IAttrValueService {

    /**
     * 根据属性值id列表查询属性列表
     * @param attrvalueIdList 属性值id列表
     * @return 属性列表
     */
    @Override
    public List<AttrValue> selectAttrValueListByIdList(List<Long> attrvalueIdList) {
        if (attrvalueIdList != null && !attrvalueIdList.isEmpty()) {
            return this.listByIds(attrvalueIdList);
        }
        return List.of();
    }
}
