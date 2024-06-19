package com.zjw.lease.web.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjw.lease.model.entity.BrowsingHistory;
import com.zjw.lease.web.app.vo.history.HistoryItemVo;

/**
 * <p>
 * 浏览历史 服务类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
public interface IBrowsingHistoryService extends IService<BrowsingHistory> {

    /**
     * 分页获取浏览历史
     * @param page 分页
     * @param userId 用户id
     */
    void pageHistoryItemVo(IPage<HistoryItemVo> page, Long userId);

    /**
     * 保存浏览历史
     * @param roomId 房间id
     */
    void saveBrowsingHistory(Long roomId);
}
