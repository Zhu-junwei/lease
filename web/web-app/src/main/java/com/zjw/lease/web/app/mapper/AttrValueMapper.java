package com.zjw.lease.web.app.mapper;

import com.zjw.lease.model.entity.AttrValue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjw.lease.web.app.vo.attr.AttrValueVo;

import java.util.List;

/**
 * <p>
 * 房间基本属性值表 Mapper 接口
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
public interface AttrValueMapper extends BaseMapper<AttrValue> {

    List<AttrValueVo> selectListByRoomId(Long id);
}
