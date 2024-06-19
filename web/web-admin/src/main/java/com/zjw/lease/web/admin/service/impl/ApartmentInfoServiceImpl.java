package com.zjw.lease.web.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjw.lease.exception.LeaseException;
import com.zjw.lease.model.entity.*;
import com.zjw.lease.model.enums.ItemType;
import com.zjw.lease.model.enums.ReleaseStatus;
import com.zjw.lease.result.ResultCodeEnum;
import com.zjw.lease.web.admin.mapper.ApartmentInfoMapper;
import com.zjw.lease.web.admin.service.*;
import com.zjw.lease.web.admin.vo.apartment.ApartmentDetailVo;
import com.zjw.lease.web.admin.vo.apartment.ApartmentItemVo;
import com.zjw.lease.web.admin.vo.apartment.ApartmentQueryVo;
import com.zjw.lease.web.admin.vo.apartment.ApartmentSubmitVo;
import com.zjw.lease.web.admin.vo.fee.FeeValueVo;
import com.zjw.lease.web.admin.vo.graph.GraphVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 公寓信息表 服务实现类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ApartmentInfoServiceImpl extends ServiceImpl<ApartmentInfoMapper, ApartmentInfo> implements IApartmentInfoService {

    private final ApartmentInfoMapper apartmentInfoMapper;
    private final IGraphInfoService graphInfoService;
    private final IApartmentFacilityService apartmentFacilityService;
    private final IApartmentLabelService apartmentLabelService;
    private final IApartmentFeeValueService apartmentFeeValueService;
    private final IApartmentRoomService apartmentRoomService;
    private final ILabelInfoService labelInfoService;
    private final IFacilityInfoService facilityInfoService;
    private final IFeeValueService feeValueService;
    private final IFeeKeyService feeKeyService;

    /**
     * 保存或更新公寓信息
     */
    @Override
    public void saveOrUpdateApartmentInfo(ApartmentSubmitVo apartmentSubmitVo) {
        // 更新公寓，先删除历史关联信息
        if (apartmentSubmitVo.getId() != null) {
            clearApartmentHistory(apartmentSubmitVo.getId());
        }

        // 保存更新公寓信息
        super.saveOrUpdate(apartmentSubmitVo);

        // 保存公寓关联信息
        saveApartmentGraphInfo(apartmentSubmitVo);
        saveApartmentFacility(apartmentSubmitVo);
        saveApartmentLabel(apartmentSubmitVo);
        saveApartmentFeeValue(apartmentSubmitVo);
    }

    /**
     * 清理公寓关联的历史信息
     */
    private void clearApartmentHistory(Long apartmentId) {
        // 删除图片信息
        Boolean removed = graphInfoService.removeByApartmentId(apartmentId);
        log.info("删除公寓图片信息: {}", removed);

        // 删除公寓配套关系信息
        removed = apartmentFacilityService.removeByApartmentId(apartmentId);
        log.info("删除公寓配套关系信息: {}", removed);

        // 删除公寓标签关系信息
        removed = apartmentLabelService.removeByApartmentId(apartmentId);
        log.info("删除公寓标签关系信息: {}", removed);

        // 删除公寓杂费值关系信息
        removed = apartmentFeeValueService.removeByApartmentId(apartmentId);
        log.info("删除公寓杂费值关系信息: {}", removed);
    }

    /**
     * 保存图片信息
     */
    private void saveApartmentGraphInfo(ApartmentSubmitVo apartmentSubmitVo) {
        List<GraphVo> graphVoList = apartmentSubmitVo.getGraphVoList();
        if (graphVoList != null && !graphVoList.isEmpty()) {
            List<GraphInfo> graphInfoList = graphVoList.stream().map(graphVo -> {
                GraphInfo graphInfo = new GraphInfo();
                graphInfo.setItemId(apartmentSubmitVo.getId());
                graphInfo.setItemType(ItemType.APARTMENT);
                graphInfo.setName(graphVo.getName());
                graphInfo.setUrl(graphVo.getUrl());
                return graphInfo;
            }).collect(Collectors.toList());
            graphInfoService.saveBatch(graphInfoList);
        }
    }

    /**
     * 保存公寓配套关系信息
     */
    private void saveApartmentFacility(ApartmentSubmitVo apartmentSubmitVo) {
        List<Long> facilityInfoIdList = apartmentSubmitVo.getFacilityInfoIds();
        if (facilityInfoIdList != null && !facilityInfoIdList.isEmpty()) {
            List<ApartmentFacility> apartmentFacilityList = facilityInfoIdList.stream().map(facilityInfoId -> {
                ApartmentFacility apartmentFacility = new ApartmentFacility();
                apartmentFacility.setApartmentId(apartmentSubmitVo.getId());
                apartmentFacility.setFacilityId(facilityInfoId);
                return apartmentFacility;
            }).toList();
            apartmentFacilityService.saveBatch(apartmentFacilityList);
        }
    }

    /**
     * 保存公寓标签关系信息
     */
    private void saveApartmentLabel(ApartmentSubmitVo apartmentSubmitVo) {
        List<Long> labelIdList = apartmentSubmitVo.getLabelIds();
        if (labelIdList != null && !labelIdList.isEmpty()) {
            List<ApartmentLabel> apartmentLabelList = labelIdList.stream().map(labelId -> {
                ApartmentLabel apartmentLabel = new ApartmentLabel();
                apartmentLabel.setApartmentId(apartmentSubmitVo.getId());
                apartmentLabel.setLabelId(labelId);
                return apartmentLabel;
            }).toList();
            apartmentLabelService.saveBatch(apartmentLabelList);
        }
    }

    /**
     * 保存公寓杂费值关系信息
     */
    private void saveApartmentFeeValue(ApartmentSubmitVo apartmentSubmitVo) {
        List<Long> feeValueIdList = apartmentSubmitVo.getFeeValueIds();
        if (feeValueIdList != null && !feeValueIdList.isEmpty()) {
            List<ApartmentFeeValue> apartmentFeeValueList = feeValueIdList.stream().map(feeValueId -> {
                ApartmentFeeValue apartmentFeeValue = new ApartmentFeeValue();
                apartmentFeeValue.setApartmentId(apartmentSubmitVo.getId());
                apartmentFeeValue.setFeeValueId(feeValueId);
                return apartmentFeeValue;
            }).toList();
            apartmentFeeValueService.saveBatch(apartmentFeeValueList);
        }
    }

    /**
     * 根据条件分页查询公寓列表
     */
    @Override
    public IPage<ApartmentItemVo> pageApartmentItemVo(IPage<ApartmentItemVo> page, ApartmentQueryVo queryVo) {
        return apartmentInfoMapper.pageApartmentItemByQuery(page, queryVo);
    }

    @Override
    public ApartmentDetailVo getApartmentDetailById(Long apartmentId) {
        //1.查询ApartmentInfo
        ApartmentInfo apartmentInfo = this.getById(apartmentId);
        if (apartmentInfo == null) {
            return null;
        }

        //2.查询图片信息 GraphInfo
        List<GraphVo> graphVoList = graphInfoService.selectListByItemTypeAndId(ItemType.APARTMENT, apartmentId);

        //3.查询标签关系LabelInfo
        List<Long> labelInfoIdList = apartmentLabelService.selectLabelInfoIdListByApartmentId(apartmentId);
        List<LabelInfo> labelInfoList = labelInfoService.listByIds(labelInfoIdList);

        //4.查询FacilityInfo
        List<Long> facilityInfoIdList = apartmentFacilityService.selectFacilityInfoIdListByApartmentId(apartmentId);
        List<FacilityInfo> facilityInfoList = facilityInfoService.listByIds(facilityInfoIdList);

        //5.查询FeeValue
        List<Long> feeValueIdList = apartmentFeeValueService.selectFeeValueIdsByApartmentId(apartmentId);
        List<FeeValue> feeValueList = feeValueService.listByIds(feeValueIdList);

        List<Long> feeKeyIdList = feeValueList.stream().map(FeeValue::getFeeKeyId).toList();
        Map<Long, FeeKey> feeKeyMap = feeKeyService.selectFeeKeyMapByFeeKeyIdList(feeKeyIdList);
        List<FeeValueVo> feeValueVoList = BeanUtil.copyToList(feeValueList, FeeValueVo.class)
                .stream()
                .peek(feeValueVo -> feeValueVo.setFeeKeyName(feeKeyMap.get(feeValueVo.getFeeKeyId()).getName()))
                .toList();

        // 封装数据
        ApartmentDetailVo adminApartmentDetailVo = new ApartmentDetailVo();
        BeanUtil.copyProperties(apartmentInfo, adminApartmentDetailVo);
        adminApartmentDetailVo.setGraphVoList(graphVoList);
        adminApartmentDetailVo.setLabelInfoList(labelInfoList);
        adminApartmentDetailVo.setFacilityInfoList(facilityInfoList);
        adminApartmentDetailVo.setFeeValueVoList(feeValueVoList);

        return adminApartmentDetailVo;
    }

    @Override
    public void removeApartmentById(Long apartmentId) {
        // 公寓下存在房间不允许删除
        Long count = apartmentRoomService.countRoomByApartmentId(apartmentId);
        if (count > 0){
            throw new LeaseException(ResultCodeEnum.ADMIN_APARTMENT_DELETE_ERROR);
        }
        clearApartmentHistory(apartmentId);
        this.removeById(apartmentId);
    }

    /**
     * 修改公寓发布状态
     * @param apartmentId 公寓id
     * @param status 发布状态
     */
    @Override
    public void updateReleaseStatusById(Long apartmentId, ReleaseStatus status) {
        LambdaUpdateWrapper<ApartmentInfo> wrapper = Wrappers.lambdaUpdate(ApartmentInfo.class)
                .eq(ApartmentInfo::getId, apartmentId)
                .set(ApartmentInfo::getIsRelease, status.getCode());
        this.update(wrapper);
    }

    /**
     * 根据区县id查询公寓信息列表
     * @param districtId 区县id
     * @return 公寓信息列表
     */
    @Override
    public List<ApartmentInfo> listInfoByDistrictId(Long districtId) {
        LambdaQueryWrapper<ApartmentInfo> wrapper = Wrappers.lambdaQuery(ApartmentInfo.class)
                .eq(ApartmentInfo::getDistrictId, districtId);
        return this.list(wrapper);
    }
}
