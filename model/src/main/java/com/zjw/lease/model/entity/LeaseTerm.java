package com.zjw.lease.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 租期
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("lease_term")
@Schema(name = "LeaseTerm", description = "租期")
public class LeaseTerm extends BaseEntity {

    @Schema(description = "租期")
    private Integer monthCount;

    @Schema(description = "租期单位")
    private String unit;
}
