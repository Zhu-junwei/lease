package com.zjw.lease.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 房间&配套关联表
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("room_facility")
@Schema(name = "RoomFacility", description = "房间&配套关联表")
public class RoomFacility extends BaseEntity {

    @Schema(description = "房间id")
    private Long roomId;

    @Schema(description = "房间设施id")
    private Long facilityId;
}
