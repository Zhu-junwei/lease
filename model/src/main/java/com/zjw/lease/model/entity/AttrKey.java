package com.zjw.lease.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 房间基本属性表
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("attr_key")
@Schema(name = "AttrKey", description = "房间基本属性表")
public class AttrKey extends BaseEntity {

    @Schema(description = "属性key")
    private String name;
}
