package com.zjw.lease.web.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjw.lease.model.entity.*;
import com.zjw.lease.model.enums.LeaseStatus;
import com.zjw.lease.web.admin.mapper.*;
import com.zjw.lease.web.admin.service.ILeaseAgreementService;
import com.zjw.lease.web.admin.vo.agreement.AgreementQueryVo;
import com.zjw.lease.web.admin.vo.agreement.AgreementVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 租约信息表 服务实现类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Service
@RequiredArgsConstructor
public class LeaseAgreementServiceImpl extends ServiceImpl<LeaseAgreementMapper, LeaseAgreement> implements ILeaseAgreementService {

    private final LeaseAgreementMapper leaseAgreementMapper;
    private final ApartmentInfoMapper apartmentInfoMapper;
    private final RoomInfoMapper roomInfoMapper;
    private final PaymentTypeMapper paymentTypeMapper;
    private final LeaseTermMapper leaseTermMapper;

    /**
     * 根据id查询租约信息
     * @param id 租约id
     * @return 租约信息
     */
    @Override
    public AgreementVo getAgreementVoById(Long id) {
        AgreementVo agreementVo = new AgreementVo();
        LeaseAgreement leaseAgreement = leaseAgreementMapper.selectById(id);
        ApartmentInfo apartmentInfo = apartmentInfoMapper.selectById(leaseAgreement.getApartmentId());
        RoomInfo roomInfo = roomInfoMapper.selectById(leaseAgreement.getRoomId());
        PaymentType paymentType = paymentTypeMapper.selectById(leaseAgreement.getPaymentTypeId());
        LeaseTerm leaseTerm = leaseTermMapper.selectById(leaseAgreement.getLeaseTermId());
        BeanUtil.copyProperties(leaseAgreement, agreementVo);
        agreementVo.setApartmentInfo(apartmentInfo);
        agreementVo.setRoomInfo(roomInfo);
        agreementVo.setPaymentType(paymentType);
        agreementVo.setLeaseTerm(leaseTerm);
        return agreementVo;
    }

    /**
     * 更新租约状态
     * @param id 租约id
     * @param status 状态
     */
    @Override
    public void updateStatusById(Long id, LeaseStatus status) {
        LambdaUpdateWrapper<LeaseAgreement> wrapper = Wrappers.lambdaUpdate(LeaseAgreement.class)
                .set(LeaseAgreement::getStatus, status)
                .eq(LeaseAgreement::getId, id);
        update(wrapper);
    }

    /**
     * 根据条件分页查询租约列表
     * @param page 分页对象
     * @param queryVo 查询条件
     * @return 租约列表
     */
    @Override
    public void pageAgreementByQuery(IPage<AgreementVo> page, AgreementQueryVo queryVo) {
        leaseAgreementMapper.pageAgreementByQuery(page, queryVo);
    }
}
