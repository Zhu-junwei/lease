package com.zjw.lease.web.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjw.lease.model.entity.LeaseTerm;

import java.util.List;

/**
 * <p>
 * 租期 Mapper 接口
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
public interface LeaseTermMapper extends BaseMapper<LeaseTerm> {

    /**
     * 根据房间id查询租期列表
     * @param id 房间id
     * @return 租期列表
     */
    List<LeaseTerm> selectListByRoomId(Long id);
}
