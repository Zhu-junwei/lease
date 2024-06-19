package com.zjw.lease.web.app.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjw.lease.constant.RedisConstant;
import com.zjw.lease.model.entity.*;
import com.zjw.lease.model.enums.ItemType;
import com.zjw.lease.web.app.mapper.*;
import com.zjw.lease.web.app.service.IApartmentInfoService;
import com.zjw.lease.web.app.service.IBrowsingHistoryService;
import com.zjw.lease.web.app.service.IRoomInfoService;
import com.zjw.lease.web.app.vo.apartment.ApartmentItemVo;
import com.zjw.lease.web.app.vo.attr.AttrValueVo;
import com.zjw.lease.web.app.vo.fee.FeeValueVo;
import com.zjw.lease.web.app.vo.graph.GraphVo;
import com.zjw.lease.web.app.vo.room.RoomDetailVo;
import com.zjw.lease.web.app.vo.room.RoomItemVo;
import com.zjw.lease.web.app.vo.room.RoomQueryVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 房间信息表 服务实现类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Service
@RequiredArgsConstructor
public class RoomInfoServiceImpl extends ServiceImpl<RoomInfoMapper, RoomInfo> implements IRoomInfoService {

    private final RoomInfoMapper roomInfoMapper;
    private final GraphInfoMapper graphInfoMapper;
    private final LeaseTermMapper leaseTermMapper;
    private final FacilityInfoMapper facilityInfoMapper;
    private final LabelInfoMapper labelInfoMapper;
    private final PaymentTypeMapper paymentTypeMapper;
    private final AttrValueMapper attrValueMapper;
    private final FeeValueMapper feeValueMapper;
    private final IBrowsingHistoryService browsingHistoryService;
    private final IApartmentInfoService apartmentInfoService;
    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * 分页查询房间列表
     *
     * @param page    分页对象
     * @param queryVo 查询条件
     * @return 分页数据
     */
    @Override
    public void pageRoomItemByQuery(Page<RoomItemVo> page, RoomQueryVo queryVo) {
        roomInfoMapper.pageRoomItemByQuery(page, queryVo);
    }

    /**
     * 根据id获取房间的详细信息
     *
     * @param id 房间id
     * @return 房间详细信息
     */
    @Override
    public RoomDetailVo getDetailById(Long id) {
        RoomDetailVo roomDetailVo = (RoomDetailVo)redisTemplate.opsForValue().get(RedisConstant.APP_ROOM_PREFIX + id);
        if (roomDetailVo == null){

            //1.查询房间信息
            RoomInfo roomInfo = roomInfoMapper.selectById(id);
            //2.查询图片
            List<GraphVo> graphVoList = graphInfoMapper.selectListByItemTypeAndId(ItemType.ROOM, id);
            //3.查询租期
            List<LeaseTerm> leaseTermList = leaseTermMapper.selectListByRoomId(id);
            //4.查询配套
            List<FacilityInfo> facilityInfoList = facilityInfoMapper.selectListByRoomId(id);
            //5.查询标签
            List<LabelInfo> labelInfoList = labelInfoMapper.selectListByRoomId(id);
            //6.查询支付方式
            List<PaymentType> paymentTypeList = paymentTypeMapper.selectListByRoomId(id);
            //7.查询基本属性
            List<AttrValueVo> attrValueVoList = attrValueMapper.selectListByRoomId(id);
            //8.查询杂费信息
            List<FeeValueVo> feeValueVoList = feeValueMapper.selectListByApartmentId(roomInfo.getApartmentId());
            //9.查询公寓信息
            ApartmentItemVo apartmentItemVo = apartmentInfoService.selectApartmentItemVoById(roomInfo.getApartmentId());

            roomDetailVo = new RoomDetailVo();
            BeanUtils.copyProperties(roomInfo, roomDetailVo);
            roomDetailVo.setApartmentItemVo(apartmentItemVo);
            roomDetailVo.setGraphVoList(graphVoList);
            roomDetailVo.setAttrValueVoList(attrValueVoList);
            roomDetailVo.setFacilityInfoList(facilityInfoList);
            roomDetailVo.setLabelInfoList(labelInfoList);
            roomDetailVo.setPaymentTypeList(paymentTypeList);
            roomDetailVo.setFeeValueVoList(feeValueVoList);
            roomDetailVo.setLeaseTermList(leaseTermList);

            // 保存浏览历史
            browsingHistoryService.saveBrowsingHistory(id);
            // 保存缓存
            redisTemplate.opsForValue().set(RedisConstant.APP_ROOM_PREFIX + id, roomDetailVo, RedisConstant.ROOM_DETAIL_TTL_HOR,
                    TimeUnit.HOURS);
        }
        // 返回结果
        return roomDetailVo;
    }

    /**
     * 分页根据公寓id分页查询房间列表
     *
     * @param page 分页对象
     * @param id   公寓id
     */
    @Override
    public void pageItemByApartmentId(Page<RoomItemVo> page, Long id) {
        roomInfoMapper.pageItemByApartmentId(page, id);
    }
}
