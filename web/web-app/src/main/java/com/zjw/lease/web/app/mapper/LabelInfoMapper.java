package com.zjw.lease.web.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjw.lease.model.entity.LabelInfo;

import java.util.List;

/**
 * <p>
 * 标签信息表 Mapper 接口
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
public interface LabelInfoMapper extends BaseMapper<LabelInfo> {

    List<LabelInfo> selectListByRoomId(Long id);

    /**
     * 根据公寓id查询标签信息
     * @param apartmentId 公寓id
     * @return 标签信息
     */
    List<LabelInfo> selectListByApartmentId(Long apartmentId);
}
