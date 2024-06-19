package com.zjw.lease.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zjw.lease.model.enums.BaseStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user_info")
@Schema(name = "UserInfo", description = "用户信息表")
public class UserInfo extends BaseEntity {

    @Schema(description = "手机号码（用做登录用户名）")
    private String phone;

    @Schema(description = "密码")
    @JsonIgnore
    private String password;

    @Schema(description = "头像url")
    private String avatarUrl;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "账号状态")
    private BaseStatus status;
}
