package com.zjw.lease.web.admin.service;

import com.zjw.lease.model.entity.FeeKey;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjw.lease.web.admin.vo.fee.FeeKeyVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 杂项费用名称表 服务类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
public interface IFeeKeyService extends IService<FeeKey> {

    List<FeeKeyVo> feeInfoList();

    /**
     * 根据feeKeyIdList查询feeKeyMap
     * @param feeKeyIdList id列表
     * @return feeKeyMap
     */
    Map<Long, FeeKey> selectFeeKeyMapByFeeKeyIdList(List<Long> feeKeyIdList);
}
