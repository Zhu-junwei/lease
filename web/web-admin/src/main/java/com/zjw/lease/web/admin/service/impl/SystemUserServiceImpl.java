package com.zjw.lease.web.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zjw.lease.model.entity.SystemPost;
import com.zjw.lease.model.entity.SystemUser;
import com.zjw.lease.model.enums.BaseStatus;
import com.zjw.lease.web.admin.mapper.SystemPostMapper;
import com.zjw.lease.web.admin.mapper.SystemUserMapper;
import com.zjw.lease.web.admin.service.ISystemUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjw.lease.web.admin.vo.system.user.SystemUserItemVo;
import com.zjw.lease.web.admin.vo.system.user.SystemUserQueryVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 员工信息表 服务实现类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Service
@RequiredArgsConstructor
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser> implements ISystemUserService {

    private final SystemUserMapper systemUserMapper;
    private final SystemPostMapper systemPostMapper;

    /**
     * 分页查询员工信息
     * @param page 分页对象
     * @param queryVo 查询条件
     */
    @Override
    public void selectSystemUserItemVoByPage(IPage<SystemUserItemVo> page, SystemUserQueryVo queryVo) {
        systemUserMapper.selectSystemUserItemVoByPage(page, queryVo);
    }

    /**
     * 根据id查询员工信息
     * @param id 员工id
     */
    @Override
    public SystemUserItemVo selectSystemUserItemVoById(Long id) {
        SystemUser systemUser = systemUserMapper.selectById(id);
        SystemPost systemPost = systemPostMapper.selectById(systemUser.getPostId());
        SystemUserItemVo systemUserItemVo = new SystemUserItemVo();
        BeanUtil.copyProperties(systemUser, systemUserItemVo);
        systemUserItemVo.setPostName(systemPost.getName());
        return systemUserItemVo;
    }

    /**
     * 根据id修改员工状态
     * @param id 员工id
     * @param status 员工状态
     */
    @Override
    public void updateStatusByUserId(Long id, BaseStatus status) {
        LambdaUpdateWrapper<SystemUser> wrapper = Wrappers.lambdaUpdate(SystemUser.class)
                .eq(SystemUser::getId, id)
                .set(SystemUser::getStatus, status);
        update(wrapper);
    }

    /**
     * 判断用户名是否可用
     * @param username 用户名
     * @return 是否可用
     */
    @Override
    public Boolean isUsernameExists(String username) {
        LambdaQueryWrapper<SystemUser> wrapper = Wrappers.lambdaQuery(SystemUser.class)
                .eq(SystemUser::getUsername, username);
        long count = count(wrapper);
        return count == 0L;
    }
}
