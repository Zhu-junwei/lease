package com.zjw.lease.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zjw.lease.model.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 杂项费用值表
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("fee_value")
@Schema(name = "FeeValue", description = "杂项费用值表")
public class FeeValue extends BaseEntity {

    @Schema(description = "费用value")
    private String name;

    @Schema(description = "收费单位")
    private String unit;

    @Schema(description = "费用所对的fee_key")
    private Long feeKeyId;
}
