package com.zjw.lease.web.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjw.lease.model.entity.ApartmentInfo;
import com.zjw.lease.model.entity.FacilityInfo;
import com.zjw.lease.model.entity.LabelInfo;
import com.zjw.lease.model.enums.ItemType;
import com.zjw.lease.web.app.mapper.*;
import com.zjw.lease.web.app.service.IApartmentInfoService;
import com.zjw.lease.web.app.vo.apartment.ApartmentDetailVo;
import com.zjw.lease.web.app.vo.apartment.ApartmentItemVo;
import com.zjw.lease.web.app.vo.graph.GraphVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 公寓信息表 服务实现类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Service
@RequiredArgsConstructor
public class ApartmentInfoServiceImpl extends ServiceImpl<ApartmentInfoMapper, ApartmentInfo> implements IApartmentInfoService {

    private final ApartmentInfoMapper apartmentInfoMapper;
    private final LabelInfoMapper labelInfoMapper;
    private final GraphInfoMapper graphInfoMapper;
    private final RoomInfoMapper roomInfoMapper;
    private final FacilityInfoMapper facilityInfoMapper;

    /**
     * 根据id获取公寓信息
     * @param apartmentId 公寓id
     * @return 公寓信息
     */
    @Override
    public ApartmentItemVo selectApartmentItemVoById(Long apartmentId) {

        ApartmentInfo apartmentInfo = apartmentInfoMapper.selectById(apartmentId);

        List<LabelInfo> labelInfoList = labelInfoMapper.selectListByApartmentId(apartmentId);

        List<GraphVo> graphVoList = graphInfoMapper.selectListByItemTypeAndId(ItemType.APARTMENT, apartmentId);

        BigDecimal minRent = roomInfoMapper.selectMinRentByApartmentId(apartmentId);

        ApartmentItemVo apartmentItemVo = new ApartmentItemVo();
        BeanUtils.copyProperties(apartmentInfo, apartmentItemVo);

        apartmentItemVo.setGraphVoList(graphVoList);
        apartmentItemVo.setLabelInfoList(labelInfoList);
        apartmentItemVo.setMinRent(minRent);
        return apartmentItemVo;
    }

    /**
     * 根据id获取公寓详细信息
     * @param apartmentId 公寓id
     * @return 公寓详细信息
     */
    @Override
    public ApartmentDetailVo getDetailById(Long apartmentId) {
        ApartmentInfo apartmentInfo = apartmentInfoMapper.selectById(apartmentId);
        List<LabelInfo> labelInfoList = labelInfoMapper.selectListByApartmentId(apartmentId);
        List<GraphVo> graphVoList = graphInfoMapper.selectListByItemTypeAndId(ItemType.APARTMENT, apartmentId);
        List<FacilityInfo> facilityInfoList = facilityInfoMapper.selectListByApartmentId(apartmentId);
        BigDecimal minRent = roomInfoMapper.selectMinRentByApartmentId(apartmentId);
        // 组装信息
        ApartmentDetailVo apartmentDetailVo = new ApartmentDetailVo();
        BeanUtils.copyProperties(apartmentInfo, apartmentDetailVo);
        apartmentDetailVo.setGraphVoList(graphVoList);
        apartmentDetailVo.setLabelInfoList(labelInfoList);
        apartmentDetailVo.setFacilityInfoList(facilityInfoList);
        apartmentDetailVo.setMinRent(minRent);
        return apartmentDetailVo;
    }
}
