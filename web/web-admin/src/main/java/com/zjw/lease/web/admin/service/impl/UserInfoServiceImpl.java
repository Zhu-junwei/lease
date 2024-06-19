package com.zjw.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjw.lease.model.entity.UserInfo;
import com.zjw.lease.model.enums.BaseStatus;
import com.zjw.lease.web.admin.mapper.UserInfoMapper;
import com.zjw.lease.web.admin.service.IUserInfoService;
import com.zjw.lease.web.admin.vo.user.UserInfoQueryVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

    private final UserInfoMapper userInfoMapper;

    /**
     * 分页查询用户信息
     *
     * @param page    分页对象
     * @param queryVo 查询信息
     * @return 分页信息
     */
    @Override
    public void pageUserInfo(Page<UserInfo> page, UserInfoQueryVo queryVo) {
        LambdaQueryWrapper<UserInfo> wrapper = Wrappers.lambdaQuery(UserInfo.class)
                .like(queryVo.getPhone() != null, UserInfo::getPhone, queryVo.getPhone())
                .eq(queryVo.getStatus() != null, UserInfo::getStatus, queryVo.getStatus());
        page(page, wrapper);
    }

    /**
     * 根据id更新账号状态
     * @param id 用户id
     * @param status 更新后的用户状态
     */
    @Override
    public void updateStatusById(Long id, BaseStatus status) {
        LambdaUpdateWrapper<UserInfo> wrapper = Wrappers.lambdaUpdate(UserInfo.class)
                .eq(UserInfo::getId, id)
                .set(UserInfo::getStatus, status);
        update(wrapper);
    }
}
