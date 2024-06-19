package com.zjw.lease.web.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjw.lease.web.app.mapper.UserInfoMapper;
import com.zjw.lease.web.app.service.IUserInfoService;
import com.zjw.lease.model.entity.UserInfo;
import com.zjw.lease.web.app.mapper.UserInfoMapper;
import com.zjw.lease.web.app.service.IUserInfoService;
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
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

}
