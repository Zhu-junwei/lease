package com.zjw.lease.web.app.mapper;

import com.zjw.lease.model.entity.FeeValue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjw.lease.web.app.vo.fee.FeeValueVo;

import java.util.List;

/**
 * <p>
 * 杂项费用值表 Mapper 接口
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
public interface FeeValueMapper extends BaseMapper<FeeValue> {

    /**
     * 根据公寓id查询 杂项费用值列表
     * @param apartmentId 公寓id
     * @return 杂项费用值列表
     */
    List<FeeValueVo> selectListByApartmentId(Long apartmentId);
}
