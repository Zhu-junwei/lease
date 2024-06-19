package com.zjw.lease.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *  省份信息表
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("province_info")
@Schema(name = "ProvinceInfo", description = "省份信息表")
public class ProvinceInfo extends BaseEntity {

    @Schema(description = "省份名称")
    private String name;
}
