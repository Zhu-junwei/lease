package com.zjw.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjw.lease.model.entity.FeeKey;
import com.zjw.lease.web.admin.mapper.FeeKeyMapper;
import com.zjw.lease.web.admin.service.IFeeKeyService;
import com.zjw.lease.web.admin.vo.fee.FeeKeyVo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 杂项费用名称表 服务实现类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Service
@AllArgsConstructor
public class FeeKeyServiceImpl extends ServiceImpl<FeeKeyMapper, FeeKey> implements IFeeKeyService {

    private final FeeKeyMapper feeKeyMapper;
    @Override
    public List<FeeKeyVo> feeInfoList() {
        return feeKeyMapper.feeInfoList();
    }

    /**
     * 根据feeKeyIdList查询feeKeyMap
     * @param feeKeyIdList id列表
     * @return feeKeyMap
     */
    @Override
    public Map<Long, FeeKey> selectFeeKeyMapByFeeKeyIdList(List<Long> feeKeyIdList) {
        return baseMapper.selectBatchIds(feeKeyIdList)
                .stream()
                .collect(Collectors.toMap(FeeKey::getId, feeKey -> feeKey));
    }
}
