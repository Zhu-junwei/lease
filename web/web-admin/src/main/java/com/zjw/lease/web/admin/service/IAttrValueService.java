package com.zjw.lease.web.admin.service;

import com.zjw.lease.model.entity.AttrValue;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 房间基本属性值表 服务类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
public interface IAttrValueService extends IService<AttrValue> {

    /**
     * 根据属性值id列表查询属性列表
     * @param attrvalueIdList 属性值id列表
     * @return 属性列表
     */
    List<AttrValue> selectAttrValueListByIdList(List<Long> attrvalueIdList);
}
