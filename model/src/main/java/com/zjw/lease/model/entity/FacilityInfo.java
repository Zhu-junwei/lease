package com.zjw.lease.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zjw.lease.model.enums.ItemType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 配套信息表
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("facility_info")
@Schema(name = "FacilityInfo", description = "配套信息表")
public class FacilityInfo extends BaseEntity {

    @Schema(description = "类型（1:公寓图片,2:房间图片）")
    private ItemType type;

    @Schema(description = "名称")
    private String name;

    private String icon;
}
