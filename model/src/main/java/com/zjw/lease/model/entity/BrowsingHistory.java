package com.zjw.lease.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * <p>
 * 浏览历史
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("browsing_history")
@Schema(name = "BrowsingHistory", description = "浏览历史")
public class BrowsingHistory extends BaseEntity {

    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "浏览房间id")
    private Long roomId;

    private LocalDateTime browseTime;
}
