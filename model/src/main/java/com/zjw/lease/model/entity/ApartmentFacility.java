package com.zjw.lease.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 公寓&配套关联表
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("apartment_facility")
@Schema(name = "ApartmentFacility", description = "公寓&配套关联表")
public class ApartmentFacility extends BaseEntity {

    @Schema(description = "公寓id")
    private Long apartmentId;

    @Schema(description = "设施id")
    private Long facilityId;
}
