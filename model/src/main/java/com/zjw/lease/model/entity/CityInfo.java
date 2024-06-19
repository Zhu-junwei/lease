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
@TableName("city_info")
@Schema(name = "CityInfo", description = "")
public class CityInfo extends BaseEntity {

    @Schema(description = "城市名称")
    private String name;

    @Schema(description = "所属省份id")
    private Integer provinceId;
}
