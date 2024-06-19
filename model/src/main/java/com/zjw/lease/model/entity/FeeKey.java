package com.zjw.lease.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 杂项费用名称表
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("fee_key")
@Schema(name = "FeeKey", description = "杂项费用名称表")
public class FeeKey extends BaseEntity {

    @Schema(description = "付款项key")
    private String name;
}
