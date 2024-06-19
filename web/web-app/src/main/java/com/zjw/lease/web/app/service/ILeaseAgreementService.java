package com.zjw.lease.web.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjw.lease.model.entity.LeaseAgreement;
import com.zjw.lease.web.app.vo.agreement.AgreementDetailVo;
import com.zjw.lease.web.app.vo.agreement.AgreementItemVo;

import java.util.List;

/**
 * <p>
 * 租约信息表 服务类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
public interface ILeaseAgreementService extends IService<LeaseAgreement> {

    List<AgreementItemVo> listItemByPhone(String phone);

    AgreementDetailVo getDetailById(Long id);
}
