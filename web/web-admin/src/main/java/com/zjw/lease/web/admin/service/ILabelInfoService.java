package com.zjw.lease.web.admin.service;

import com.zjw.lease.model.entity.LabelInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 标签信息表 服务类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
public interface ILabelInfoService extends IService<LabelInfo> {

    /**
     * 根据房间id查询标签信息
     * @param roomId 房间id
     * @return 标签列表
     */
    List<LabelInfo> selectListByRoomId(Long roomId);
}
