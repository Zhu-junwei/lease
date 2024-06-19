package com.zjw.lease.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 房间租期管理表
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("room_lease_term")
@Schema(name = "RoomLeaseTerm", description = "房间租期管理表")
public class RoomLeaseTerm extends BaseEntity {

    @Schema(description = "房间id")
    private Long roomId;

    @Schema(description = "租期id")
    private Long leaseTermId;
}
