package com.zjw.lease.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 房间基本属性值表
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("attr_value")
@Schema(name = "AttrValue", description = "房间基本属性值表")
public class AttrValue extends BaseEntity {

    @Schema(description = "属性value")
    private String name;

    @Schema(description = "对应的属性key_id")
    private Long attrKeyId;
}
