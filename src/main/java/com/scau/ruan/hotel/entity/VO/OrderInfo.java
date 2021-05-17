package com.scau.ruan.hotel.entity.VO;

import com.scau.ruan.hotel.entity.OrderDetail;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderInfo {

    @ApiModelProperty(value = "处理人id")
    private Integer userId;

    @ApiModelProperty(value = "订单Id")
    private Integer orderId;

    @ApiModelProperty(value = "买方姓名")
    private String buyerName;

    @ApiModelProperty(value = "买方联系方式")
    private String buyerPhone;

    @ApiModelProperty(value = "总金额")
    private BigDecimal amount;

    @ApiModelProperty(value = "付款状态")
    private Integer payStatus;

    @ApiModelProperty(value = "订单状态")
    private Integer orderStatus;

    @ApiModelProperty(value = "订单详情数组")
    private OrderDetail[] orderDetails;

}
