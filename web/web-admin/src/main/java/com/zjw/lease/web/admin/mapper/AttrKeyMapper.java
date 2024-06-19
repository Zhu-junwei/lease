package com.zjw.lease.web.admin.mapper;

import com.zjw.lease.model.entity.AttrKey;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjw.lease.web.admin.vo.attr.AttrKeyVo;

import java.util.List;

/**
 * <p>
 * 房间基本属性表 Mapper 接口
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
public interface AttrKeyMapper extends BaseMapper<AttrKey> {

    List<AttrKeyVo> listAttrInfo();
}
