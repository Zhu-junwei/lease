package com.zjw.lease.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("district_info")
@Schema(name = "DistrictInfo", description = "")
public class DistrictInfo extends BaseEntity {

    @Schema(description = "区域名称")
    private String name;

    @Schema(description = "所属城市id")
    private Integer cityId;
}
