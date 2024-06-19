package com.zjw.lease.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zjw.lease.model.enums.ReleaseStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * <p>
 * 房间信息表
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("room_info")
@Schema(name = "RoomInfo", description = "房间信息表")
public class RoomInfo extends BaseEntity {

    @Schema(description = "房间号")
    private String roomNumber;

    @Schema(description = "租金（元/月）")
    private BigDecimal rent;

    @Schema(description = "所属公寓id")
    private Long apartmentId;

    @Schema(description = "是否发布")
    private ReleaseStatus isRelease;
}
