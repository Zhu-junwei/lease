package com.zjw.lease.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zjw.lease.model.enums.ReleaseStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 公寓信息表
 * </p>
 *
 * @author zjw
 * @since 2024-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("apartment_info")
@Schema(name = "ApartmentInfo", description = "公寓信息表")
public class ApartmentInfo extends BaseEntity {

    @Schema(description = "公寓名称")
    private String name;

    @Schema(description = "公寓介绍")
    private String introduction;

    @Schema(description = "所处区域id")
    private Long districtId;

    @Schema(description = "区域名称")
    private String districtName;

    @Schema(description = "所处城市id")
    private Long cityId;

    @Schema(description = "城市名称")
    private String cityName;

    @Schema(description = "所处省份id")
    private Long provinceId;

    @Schema(description = "省份名称")
    private String provinceName;

    @Schema(description = "详细地址")
    private String addressDetail;

    @Schema(description = "经度")
    private String latitude;

    @Schema(description = "纬度")
    private String longitude;

    @Schema(description = "公寓前台电话")
    private String phone;

    @Schema(description = "是否发布（1:发布，0:未发布）")
    private ReleaseStatus isRelease;
}
