package com.zjw.lease.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zjw.lease.model.enums.BaseStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 员工信息表
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("system_user")
@Schema(name = "SystemUser", description = "员工信息表")
public class SystemUser extends BaseEntity {

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "密码")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Schema(description = "姓名")
    private String name;

    @Schema(description = "用户类型")
    private Byte type;

    @Schema(description = "手机号码")
    private String phone;

    @Schema(description = "头像地址")
    private String avatarUrl;

    @Schema(description = "备注信息")
    private String additionalInfo;

    @Schema(description = "岗位id")
    private Long postId;

    @Schema(description = "账号状态")
    private BaseStatus status;
}
