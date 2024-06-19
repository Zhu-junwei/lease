package com.zjw.lease.web.admin.service;

import com.zjw.lease.model.entity.AttrKey;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjw.lease.web.admin.vo.attr.AttrKeyVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 房间基本属性表 服务类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
public interface IAttrKeyService extends IService<AttrKey> {

    List<AttrKeyVo> listAttrInfo();

    /**
     * 根据attrKeyIdList查询attrKeyMap
     * @param attrKeyIdList attrKeyId集合
     * @return attrKeyMap
     */
    Map<Long, AttrKey> selectAttrKeyMapByAttrKeyIdList(List<Long> attrKeyIdList);
}
