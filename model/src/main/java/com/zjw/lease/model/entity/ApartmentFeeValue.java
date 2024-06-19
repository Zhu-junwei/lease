package com.zjw.lease.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 公寓&杂费关联表
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("apartment_fee_value")
@Schema(name = "ApartmentFeeValue", description = "公寓&杂费关联表")
public class ApartmentFeeValue extends BaseEntity {

    @Schema(description = "公寓id")
    private Long apartmentId;

    @Schema(description = "收费项value_id")
    private Long feeValueId;
}
