package com.zjw.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjw.lease.model.entity.LabelInfo;
import com.zjw.lease.web.admin.mapper.LabelInfoMapper;
import com.zjw.lease.web.admin.service.ILabelInfoService;
import com.zjw.lease.web.admin.service.IRoomLabelService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 标签信息表 服务实现类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Service
@AllArgsConstructor
public class LabelInfoServiceImpl extends ServiceImpl<LabelInfoMapper, LabelInfo> implements ILabelInfoService {

    private final IRoomLabelService roomLabelService;

    /**
     * 根据房间id查询标签信息
     * @param roomId 房间id
     * @return 标签列表
     */
    @Override
    public List<LabelInfo> selectListByRoomId(Long roomId) {
        List<Long> labelInfoIdList = roomLabelService.selectRoomLabelIdListByRoomId(roomId);
        if (labelInfoIdList.isEmpty()) {
            return Collections.emptyList();
        }
        return this.listByIds(labelInfoIdList);
    }
}
