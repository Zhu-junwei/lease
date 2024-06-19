package com.zjw.lease.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 公寓标签关联表
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("apartment_label")
@Schema(name = "ApartmentLabel", description = "公寓标签关联表")
public class ApartmentLabel extends BaseEntity {

    @Schema(description = "公寓id")
    private Long apartmentId;

    @Schema(description = "标签id")
    private Long labelId;
}
