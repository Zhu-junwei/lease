package com.zjw.lease.web.app.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjw.lease.login.LoginUserHolder;
import com.zjw.lease.model.entity.BrowsingHistory;
import com.zjw.lease.web.app.mapper.BrowsingHistoryMapper;
import com.zjw.lease.web.app.service.IBrowsingHistoryService;
import com.zjw.lease.web.app.vo.history.HistoryItemVo;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 浏览历史 服务实现类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Service
@RequiredArgsConstructor
public class BrowsingHistoryServiceImpl extends ServiceImpl<BrowsingHistoryMapper, BrowsingHistory> implements IBrowsingHistoryService {

    private final BrowsingHistoryMapper browsingHistoryMapper;

    /**
     * 分页获取浏览历史
     * @param page 分页
     * @param userId 用户id
     */
    @Override
    public void pageHistoryItemVo(IPage<HistoryItemVo> page, Long userId) {
        browsingHistoryMapper.pageHistoryItemVo(page, userId);
    }

    /**
     * 保存浏览历史
     * @param roomId 房间id
     */
    @Override
    @Async
    public void saveBrowsingHistory(Long roomId) {
        // 保存浏览历史
        BrowsingHistory browsingHistory = browsingHistoryMapper.selectOne(
                Wrappers.lambdaQuery(BrowsingHistory.class)
                        .eq(BrowsingHistory::getUserId, LoginUserHolder.getLoginUser().getUserId())
                        .eq(BrowsingHistory::getRoomId, roomId)
        );
        if (browsingHistory == null){
            browsingHistory = new BrowsingHistory();
            browsingHistory.setUserId(LoginUserHolder.getLoginUser().getUserId());
            browsingHistory.setRoomId(roomId);
            browsingHistory.setBrowseTime(LocalDateTime.now());
        }
        browsingHistory.setBrowseTime(LocalDateTime.now());
        saveOrUpdate(browsingHistory);
    }
}
