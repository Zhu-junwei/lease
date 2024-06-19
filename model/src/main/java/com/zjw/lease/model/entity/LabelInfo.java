package com.zjw.lease.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zjw.lease.model.enums.ItemType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 标签信息表
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("label_info")
@Schema(name = "LabelInfo", description = "标签信息表")
public class LabelInfo extends BaseEntity {

    @Schema(description = "类型（1:公寓标签,2:房间标签）")
    private ItemType type;

    @Schema(description = "标签名称")
    private String name;
}
