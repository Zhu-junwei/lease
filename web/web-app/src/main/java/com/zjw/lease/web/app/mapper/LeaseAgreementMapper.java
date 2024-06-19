package com.zjw.lease.web.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjw.lease.model.entity.LeaseAgreement;
import com.zjw.lease.web.app.vo.agreement.AgreementItemVo;

import java.util.List;

/**
 * <p>
 * 租约信息表 Mapper 接口
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
public interface LeaseAgreementMapper extends BaseMapper<LeaseAgreement> {

    List<AgreementItemVo> listItemByPhone(String phone);
}
