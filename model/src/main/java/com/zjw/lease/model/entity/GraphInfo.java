package com.zjw.lease.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zjw.lease.model.enums.ItemType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 图片信息表
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("graph_info")
@Schema(name = "GraphInfo", description = "图片信息表")
public class GraphInfo extends BaseEntity {

    @Schema(description = "图片名称")
    private String name;

    @Schema(description = "图片所属对象类型（1:apartment,2:room）")
    private ItemType itemType;

    @Schema(description = "图片所有对象id")
    private Long itemId;

    @Schema(description = "图片地址")
    private String url;
}
