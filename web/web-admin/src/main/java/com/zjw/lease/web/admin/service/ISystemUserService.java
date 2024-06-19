package com.zjw.lease.web.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjw.lease.model.entity.SystemUser;
import com.zjw.lease.model.enums.BaseStatus;
import com.zjw.lease.web.admin.vo.system.user.SystemUserItemVo;
import com.zjw.lease.web.admin.vo.system.user.SystemUserQueryVo;

/**
 * <p>
 * 员工信息表 服务类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
public interface ISystemUserService extends IService<SystemUser> {

    /**
     * 分页查询员工信息
     * @param page 分页对象
     * @param queryVo 查询条件
     */
    void selectSystemUserItemVoByPage(IPage<SystemUserItemVo> page, SystemUserQueryVo queryVo);

    /**
     * 根据id查询员工信息
     * @param id 员工id
     * @return 员工信息
     */
    SystemUserItemVo selectSystemUserItemVoById(Long id);

    /**
     * 根据id修改员工状态
     * @param id 员工id
     * @param status 员工状态
     */
    void updateStatusByUserId(Long id, BaseStatus status);

    /**
     * 判断用户名是否可用
     * @param username 用户名
     * @return 是否可用
     */
    Boolean isUsernameExists(String username);
}
