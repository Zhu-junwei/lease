package com.zjw.lease.web.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zjw.lease.model.entity.LeaseAgreement;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjw.lease.model.enums.LeaseStatus;
import com.zjw.lease.web.admin.vo.agreement.AgreementQueryVo;
import com.zjw.lease.web.admin.vo.agreement.AgreementVo;

/**
 * <p>
 * 租约信息表 服务类
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
public interface ILeaseAgreementService extends IService<LeaseAgreement> {

    /**
     * 根据id查询租约信息
     * @param id 租约id
     * @return 租约信息
     */
    AgreementVo getAgreementVoById(Long id);

    /**
     * 更新租约状态
     * @param id 租约id
     * @param status 状态
     */
    void updateStatusById(Long id, LeaseStatus status);

    /**
     * 根据条件分页查询租约列表
     * @param page 分页对象
     * @param queryVo 查询条件
     */
    void pageAgreementByQuery(IPage<AgreementVo> page, AgreementQueryVo queryVo);
}
