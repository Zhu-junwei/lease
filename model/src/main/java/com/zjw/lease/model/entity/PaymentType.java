package com.zjw.lease.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 支付方式表
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("payment_type")
@Schema(name = "PaymentType", description = "支付方式表")
public class PaymentType extends BaseEntity {

    @Schema(description = "付款方式名称")
    private String name;

    @Schema(description = "每次支付租期数")
    private Integer payMonthCount;

    @Schema(description = "付费说明")
    private String additionalInfo;
}
