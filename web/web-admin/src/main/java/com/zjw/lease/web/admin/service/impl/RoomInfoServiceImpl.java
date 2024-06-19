package com.zjw.lease.web.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjw.lease.constant.RedisConstant;
import com.zjw.lease.model.entity.*;
import com.zjw.lease.model.enums.ItemType;
import com.zjw.lease.model.enums.ReleaseStatus;
import com.zjw.lease.web.admin.mapper.RoomInfoMapper;
import com.zjw.lease.web.admin.service.*;
import com.zjw.lease.web.admin.vo.attr.AttrValueVo;
import com.zjw.lease.web.admin.vo.graph.GraphVo;
import com.zjw.lease.web.admin.vo.room.RoomDetailVo;
import com.zjw.lease.web.admin.vo.room.RoomItemVo;
import com.zjw.lease.web.admin.vo.room.RoomQueryVo;
import com.zjw.lease.web.admin.vo.room.RoomSubmitVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * <p>
 * 房间信息表 服务实现类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class RoomInfoServiceImpl extends ServiceImpl<RoomInfoMapper, RoomInfo> implements IRoomInfoService {

    private final RoomInfoMapper roomInfoMapper;
    private final IApartmentRoomService apartmentRoomService;
    private final IGraphInfoService graphInfoService;
    private final IRoomFacilityService roomFacilityService;
    private final IFacilityInfoService facilityInfoService;
    private final IRoomLabelService roomLabelService;
    private final ILabelInfoService labelInfoService;
    private final IRoomAttrValueService roomAttrValueService;
    private final IAttrValueService attrValueService;
    private final IAttrKeyService attributeKeyService;
    private final IRoomPaymentTypeService roomPaymentTypeService;
    private final IRoomLeaseTermService roomLeaseTermService;
    private final ILeaseTermService leaseTermService;
    private final IPaymentTypeService paymentTypeService;
    private final RedisTemplate<String, Object> redisTemplate;


    /**
     * 保存或更新房间信息
     *
     * @param roomSubmitVo 房间信息
     */
    @Override
    public void saveOrUpdateRoomInfo(RoomSubmitVo roomSubmitVo) {

        // 更新公寓，先删除历史关联信息
        if (roomSubmitVo.getId() != null) {
            // 删除历史关联信息
            clearRoomInfoHistory(roomSubmitVo.getId());
            // 清除缓存
            clearRoomSubmitVoCache(roomSubmitVo.getId());
        }

        // 保存更新房间信息
        this.saveOrUpdate(roomSubmitVo);

        // 保存房间关联信息
        saveRoomGraphInfo(roomSubmitVo);
        saveRoomFacility(roomSubmitVo);
        saveRoomLabel(roomSubmitVo);
        saveRoomAttrValue(roomSubmitVo);
        saveRoomPaymentType(roomSubmitVo);
        saveRoomLeaseTerm(roomSubmitVo);
    }

    private void saveRoomLeaseTerm(RoomSubmitVo roomSubmitVo) {
        List<Long> leaseTermIdList = roomSubmitVo.getLeaseTermIds();
        if (leaseTermIdList != null && !leaseTermIdList.isEmpty()) {
            List<RoomLeaseTerm> roomLeaseTermList = leaseTermIdList.stream().map(leaseTermId -> {
                RoomLeaseTerm roomLeaseTerm = new RoomLeaseTerm();
                roomLeaseTerm.setLeaseTermId(leaseTermId);
                roomLeaseTerm.setRoomId(roomSubmitVo.getId());
                return roomLeaseTerm;
            }).toList();
            roomLeaseTermService.saveBatch(roomLeaseTermList);
        }
    }

    private void saveRoomPaymentType(RoomSubmitVo roomSubmitVo) {
        List<Long> paymentTypeIdList = roomSubmitVo.getPaymentTypeIds();
        if (paymentTypeIdList != null && !paymentTypeIdList.isEmpty()) {
            List<RoomPaymentType> roomPaymentTypeList = paymentTypeIdList.stream().map(paymentTypeId -> {
                RoomPaymentType roomPaymentType = new RoomPaymentType();
                roomPaymentType.setPaymentTypeId(paymentTypeId);
                roomPaymentType.setRoomId(roomSubmitVo.getId());
                return roomPaymentType;
            }).toList();
            roomPaymentTypeService.saveBatch(roomPaymentTypeList);
        }
    }

    private void saveRoomAttrValue(RoomSubmitVo roomSubmitVo) {
        List<Long> attrValueIdList = roomSubmitVo.getAttrValueIds();
        if (attrValueIdList != null && !attrValueIdList.isEmpty()) {
            List<RoomAttrValue> roomAttr = attrValueIdList.stream().map(attrValueId -> {
                RoomAttrValue roomAttrValue = new RoomAttrValue();
                roomAttrValue.setAttrValueId(attrValueId);
                roomAttrValue.setRoomId(roomSubmitVo.getId());
                return roomAttrValue;
            }).toList();
            roomAttrValueService.saveBatch(roomAttr);
        }
    }

    private void saveRoomLabel(RoomSubmitVo roomSubmitVo) {
        List<Long> labelIdList = roomSubmitVo.getLabelInfoIds();
        if (labelIdList != null && !labelIdList.isEmpty()) {
            List<RoomLabel> roomLabelList = labelIdList.stream().map(labelId -> {
                RoomLabel roleLabel = new RoomLabel();
                roleLabel.setLabelId(labelId);
                roleLabel.setRoomId(roomSubmitVo.getId());
                return roleLabel;
            }).toList();
            roomLabelService.saveBatch(roomLabelList);
        }
    }

    private void saveRoomFacility(RoomSubmitVo roomSubmitVo) {
        List<Long> facilityInfoIdList = roomSubmitVo.getFacilityInfoIds();
        if (facilityInfoIdList != null && !facilityInfoIdList.isEmpty()) {
            List<RoomFacility> roomFacilityList = facilityInfoIdList.stream().map(facilityInfoId -> {
                RoomFacility roomFacility = new RoomFacility();
                roomFacility.setFacilityId(facilityInfoId);
                roomFacility.setRoomId(roomSubmitVo.getId());
                return roomFacility;
            }).toList();
            roomFacilityService.saveBatch(roomFacilityList);
        }
    }

    private void saveRoomGraphInfo(RoomSubmitVo roomSubmitVo) {
        List<GraphVo> graphVoList = roomSubmitVo.getGraphVoList();
        if (graphVoList != null && !graphVoList.isEmpty()) {
            List<GraphInfo> graphInfoList = graphVoList.stream().map(graphVo -> {
                GraphInfo graphInfo = new GraphInfo();
                graphInfo.setItemId(roomSubmitVo.getId());
                graphInfo.setItemType(ItemType.ROOM);
                graphInfo.setName(graphVo.getName());
                graphInfo.setUrl(graphVo.getUrl());
                return graphInfo;
            }).collect(Collectors.toList());
            graphInfoService.saveBatch(graphInfoList);
        }
    }

    /**
     * 删除房间历史关联信息
     *
     * @param roomInfoId 房间id
     */
    private void clearRoomInfoHistory(Long roomInfoId) {
        // 删除图片信息
        Boolean removed = graphInfoService.removeByRoomInfoId(roomInfoId);
        log.info("删除房间图片信息: {}", removed);

        // 删除房间配套关系信息
        removed = roomFacilityService.removeByRoomInfoId(roomInfoId);
        log.info("删除房间配套关系信息: {}", removed);

        // 删除房间标签关系信息
        removed = roomLabelService.removeByRoomInfoId(roomInfoId);
        log.info("删除房间标签关系信息: {}", removed);

        // 删除房间属性值关系
        removed = roomAttrValueService.removeByRoomInfoId(roomInfoId);
        log.info("删除房间属性值关系: {}", removed);

        // 删除房间支付方式关系
        removed = roomPaymentTypeService.removeByRoomInfoId(roomInfoId);
        log.info("删除房间支付方式关系: {}", removed);

        // 删除房间租期关系
        removed = roomLeaseTermService.removeByRoomInfoId(roomInfoId);
        log.info("删除房间租期关系: {}", removed);
    }

    /**
     * 清除缓存
     * @param roomId 房间id
     */
    private void clearRoomSubmitVoCache(Long roomId){
        redisTemplate.delete(RedisConstant.APP_ROOM_PREFIX + roomId);
    }

    /**
     * 根据id删除房间信息
     *
     * @param roomId 房间id
     */
    @Override
    public void removeRoomById(Long roomId) {
        clearRoomInfoHistory(roomId);
        clearRoomSubmitVoCache(roomId);
        this.removeById(roomId);
    }

    /**
     * 根据id修改房间发布状态
     *
     * @param roomId 房间id
     * @param status 发布状态
     */
    @Override
    public void updateReleaseStatusById(Long roomId, ReleaseStatus status) {
        LambdaUpdateWrapper<RoomInfo> wrapper = Wrappers.lambdaUpdate(RoomInfo.class)
                .eq(RoomInfo::getId, roomId)
                .set(RoomInfo::getIsRelease, status);
        this.update(wrapper);
    }

    @Override
    public List<RoomInfo> listBasicByApartmentId(Long apartmentId) {
        LambdaQueryWrapper<RoomInfo> wrapper = Wrappers.lambdaQuery(RoomInfo.class)
                .eq(RoomInfo::getApartmentId, apartmentId);
        return this.list(wrapper);
    }

    /**
     * 根据条件分页查询房间列表
     *
     * @param page    分页对象
     * @param queryVo 查询条件
     * @return 房间列表
     */
    @Override
    public IPage<RoomItemVo> pageRoomItemVo(IPage<RoomItemVo> page, RoomQueryVo queryVo) {
        return roomInfoMapper.pageRoomItemByQuery(page, queryVo);
    }

    /**
     * 根据id获取房间详细信息
     *
     * @param roomId 房间id
     * @return 房间详细信息
     */
    @Override
    public RoomDetailVo getDetailById(Long roomId) {
        RoomDetailVo roomDetailVo = (RoomDetailVo)redisTemplate.opsForValue().get(RedisConstant.APP_ROOM_PREFIX + roomId);
        if (roomDetailVo == null) {
            //1.查询RoomInfo
            RoomInfo roomInfo = roomInfoMapper.selectById(roomId);
            //2.查询所属公寓信息
            ApartmentInfo apartmentInfo = apartmentRoomService.getApartmentInfoById(roomInfo.getApartmentId());
            //3.查询graphInfoList
            List<GraphVo> graphVoList = graphInfoService.selectListByItemTypeAndId(ItemType.ROOM, roomId);

            //4.查询attrValueList
            List<AttrValueVo> attrvalueVoList = Collections.emptyList();
            List<Long> attrvalueIdList = roomAttrValueService.selectAttrValueIdListByRoomId(roomId);
            if (attrvalueIdList != null && !attrvalueIdList.isEmpty()) {
                List<AttrValue> attrValueList = attrValueService.selectAttrValueListByIdList(attrvalueIdList);
                if (attrValueList != null && !attrValueList.isEmpty()) {
                    List<Long> attrKeyIdList = attrValueList.stream().map(AttrValue::getAttrKeyId).toList();
                    Map<Long, AttrKey> attrKeyMap = attributeKeyService.selectAttrKeyMapByAttrKeyIdList(attrKeyIdList);
                    attrvalueVoList = BeanUtil.copyToList(attrValueList, AttrValueVo.class)
                            .stream()
                            .peek(attrValueVo -> attrValueVo.setAttrKeyName(attrKeyMap.get(attrValueVo.getAttrKeyId())
                                    .getName()))
                            .toList();
                }
            }

            //5.查询facilityInfoList
            List<FacilityInfo> facilityInfoList = facilityInfoService.selectListByRoomId(roomId);
            //6.查询labelInfoList
            List<LabelInfo> labelInfoList = labelInfoService.selectListByRoomId(roomId);
            //7.查询paymentTypeList
            List<PaymentType> paymentTypeList = paymentTypeService.selectListByRoomId(roomId);
            //8.查询leaseTermList
            List<LeaseTerm> leaseTermList = leaseTermService.selectListByRoomId(roomId);

            roomDetailVo = new RoomDetailVo();
            BeanUtil.copyProperties(roomInfo, roomDetailVo);
            roomDetailVo.setApartmentInfo(apartmentInfo);
            roomDetailVo.setGraphVoList(graphVoList);
            roomDetailVo.setAttrValueVoList(attrvalueVoList);
            roomDetailVo.setFacilityInfoList(facilityInfoList);
            roomDetailVo.setLabelInfoList(labelInfoList);
            roomDetailVo.setPaymentTypeList(paymentTypeList);
            roomDetailVo.setLeaseTermList(leaseTermList);
            // 保存缓存
            redisTemplate.opsForValue().set(RedisConstant.APP_ROOM_PREFIX + roomId, roomDetailVo, RedisConstant.ROOM_DETAIL_TTL_HOR,
                    TimeUnit.HOURS);
        }
        return roomDetailVo;
    }
}
