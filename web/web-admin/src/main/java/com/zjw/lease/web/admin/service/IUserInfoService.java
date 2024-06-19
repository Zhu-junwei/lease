package com.zjw.lease.web.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjw.lease.model.entity.UserInfo;
import com.zjw.lease.model.enums.BaseStatus;
import com.zjw.lease.web.admin.vo.user.UserInfoQueryVo;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
public interface IUserInfoService extends IService<UserInfo> {

    /**
     * 分页查询用户信息
     * @param page 分页对象
     * @param queryVo 查询信息
     */
    void pageUserInfo(Page<UserInfo> page, UserInfoQueryVo queryVo);

    /**
     * 根据id更新账号状态
     * @param id 用户id
     * @param status 更新后的用户状态
     */
    void updateStatusById(Long id, BaseStatus status);
}
