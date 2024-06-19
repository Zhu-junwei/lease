package com.zjw.lease.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 房间&支付方式关联表
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("room_payment_type")
@Schema(name = "RoomPaymentType", description = "房间&支付方式关联表")
public class RoomPaymentType extends BaseEntity {

    @Schema(description = "房间id")
    private Long roomId;

    @Schema(description = "支付类型id")
    private Long paymentTypeId;
}
