package com.zjw.lease.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zjw.lease.model.enums.LeaseSourceType;
import com.zjw.lease.model.enums.LeaseStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * <p>
 * 租约信息表
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("lease_agreement")
@Schema(name = "LeaseAgreement", description = "租约信息表")
public class LeaseAgreement extends BaseEntity {

    @Schema(description = "承租人手机号码")
    private String phone;

    @Schema(description = "承租人姓名")
    private String name;

    @Schema(description = "承租人身份证号码")
    private String identificationNumber;

    @Schema(description = "签约公寓id")
    private Long apartmentId;

    @Schema(description = "签约房间id")
    private Long roomId;

    @Schema(description = "租约开始日期")
    private LocalDate leaseStartDate;

    @Schema(description = "租约结束日期")
    private LocalDate leaseEndDate;

    @Schema(description = "租期id")
    private Long leaseTermId;

    @Schema(description = "租金（元/月）")
    private BigDecimal rent;

    @Schema(description = "押金（元）")
    private BigDecimal deposit;

    @Schema(description = "支付类型id	")
    private Long paymentTypeId;

    @Schema(description = "租约状态（1:签约待确认，2:已签约，3:已取消，4:已到期，5:退租待确认，6:已退租，7:续约待确认）")
    private LeaseStatus status;

    @Schema(description = "租约来源（1:新签，2:续约）")
    private LeaseSourceType sourceType;

    @Schema(description = "备注信息")
    private String additionalInfo;
}
