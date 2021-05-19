package com.scau.ruan.hotel.entity.VO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RoomRemain {
    @ApiModelProperty(value = "房间类型")
    private Integer typeId;

    @ApiModelProperty(value = "房间状态")
    private Integer roomStatus;

    @ApiModelProperty(value = "房间类型简称")
    private String typeName;

    @ApiModelProperty(value = "床位")
    private Integer bedNum;

    @ApiModelProperty(value = "单价")
    private BigDecimal price;

    @ApiModelProperty(value = "简介")
    private String introduction;

    @ApiModelProperty(value = "剩余数量")
    private Integer remainNum;
}
