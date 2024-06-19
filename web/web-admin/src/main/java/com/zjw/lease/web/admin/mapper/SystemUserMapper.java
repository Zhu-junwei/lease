package com.zjw.lease.web.admin.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zjw.lease.model.entity.SystemUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjw.lease.web.admin.vo.system.user.SystemUserItemVo;
import com.zjw.lease.web.admin.vo.system.user.SystemUserQueryVo;

/**
 * <p>
 * 员工信息表 Mapper 接口
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
public interface SystemUserMapper extends BaseMapper<SystemUser> {

    IPage<SystemUserItemVo> selectSystemUserItemVoByPage(IPage<SystemUserItemVo> page, SystemUserQueryVo queryVo);

    default SystemUser selectByUsername(String username){
        LambdaQueryWrapper<SystemUser> wrapper = Wrappers.lambdaQuery(SystemUser.class)
                .eq(SystemUser::getUsername, username);
        return selectOne(wrapper);
    }
}
