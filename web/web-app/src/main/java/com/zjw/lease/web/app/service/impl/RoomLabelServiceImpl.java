package com.zjw.lease.web.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjw.lease.web.app.mapper.RoomLabelMapper;
import com.zjw.lease.web.app.service.IRoomLabelService;
import com.zjw.lease.model.entity.RoomLabel;
import com.zjw.lease.web.app.mapper.RoomLabelMapper;
import com.zjw.lease.web.app.service.IRoomLabelService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 房间&标签关联表 服务实现类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Service
public class RoomLabelServiceImpl extends ServiceImpl<RoomLabelMapper, RoomLabel> implements IRoomLabelService {

}
