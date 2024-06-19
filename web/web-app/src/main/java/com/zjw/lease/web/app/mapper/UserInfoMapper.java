package com.zjw.lease.web.app.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zjw.lease.model.entity.UserInfo;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    /**
     * 根据手机号查询用户信息
     * @param phone 手机号
     * @return 用户信息
     */
    default UserInfo selectByPhone(String phone) {
        LambdaQueryWrapper<UserInfo> wrapper = Wrappers.lambdaQuery(UserInfo.class)
                .eq(UserInfo::getPhone, phone);
        return selectOne(wrapper);
    }
}
