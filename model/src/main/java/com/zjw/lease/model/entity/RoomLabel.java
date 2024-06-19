package com.zjw.lease.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 房间&标签关联表
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("room_label")
@Schema(name = "RoomLabel", description = "房间&标签关联表")
public class RoomLabel extends BaseEntity {

    @Schema(description = "房间id")
    private Long roomId;

    @Schema(description = "标签id")
    private Long labelId;
}
