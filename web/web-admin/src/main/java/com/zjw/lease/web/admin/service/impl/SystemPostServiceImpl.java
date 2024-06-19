package com.zjw.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zjw.lease.model.entity.SystemPost;
import com.zjw.lease.model.enums.BaseStatus;
import com.zjw.lease.web.admin.mapper.SystemPostMapper;
import com.zjw.lease.web.admin.service.ISystemPostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 岗位信息表 服务实现类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Service
@RequiredArgsConstructor
public class SystemPostServiceImpl extends ServiceImpl<SystemPostMapper, SystemPost> implements ISystemPostService {

    /**
     * 根据id修改状态
     * @param id 岗位id
     * @param status 修改后的状态
     */
    @Override
    public void updateStatusByPostId(Long id, BaseStatus status) {
        LambdaUpdateWrapper<SystemPost> wrapper = Wrappers.lambdaUpdate(SystemPost.class)
                .eq(SystemPost::getId, id)
                .set(SystemPost::getStatus, status);
        update(wrapper);
    }
}
