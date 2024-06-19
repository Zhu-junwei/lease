package com.zjw.lease.web.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zjw.lease.model.entity.BrowsingHistory;
import com.zjw.lease.web.app.vo.history.HistoryItemVo;

/**
 * <p>
 * 浏览历史 Mapper 接口
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
public interface BrowsingHistoryMapper extends BaseMapper<BrowsingHistory> {

    /**
     * 分页获取浏览历史
     * @param page 分页
     * @param userId 用户id
     */
    IPage<HistoryItemVo> pageHistoryItemVo(IPage<HistoryItemVo> page, Long userId);
}
