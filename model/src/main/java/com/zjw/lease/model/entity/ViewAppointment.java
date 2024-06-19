package com.zjw.lease.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zjw.lease.model.enums.AppointmentStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * <p>
 * 预约看房信息表
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("view_appointment")
@Schema(name = "ViewAppointment", description = "预约看房信息表")
public class ViewAppointment extends BaseEntity {

    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "用户姓名")
    private String name;

    @Schema(description = "用户手机号码")
    private String phone;

    @Schema(description = "公寓id")
    private Long apartmentId;

    @Schema(description = "预约时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime appointmentTime;

    @Schema(description = "备注信息")
    private String additionalInfo;

    @Schema(description = "预约状态（1:待看房，2:已取消，3已看房）")
    private AppointmentStatus appointmentStatus;
}
