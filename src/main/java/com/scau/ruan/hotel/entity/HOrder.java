package com.scau.ruan.hotel.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author bangtidy
 * @since 2021-05-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="HOrder对象", description="")
public class HOrder implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "订单id")
    @TableId(value = "order_id", type = IdType.AUTO)
    private Integer orderId;

    @ApiModelProperty(value = "处理人id")
    private Integer userId;

    @ApiModelProperty(value = "买方姓名")
    private String buyerName;

    @ApiModelProperty(value = "买方联系方式")
    private String buyerPhone;

    @ApiModelProperty(value = "订单创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "订单修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "总金额")
    private BigDecimal amount;

    @ApiModelProperty(value = "付款状态")
    private Integer payStatus;

    @ApiModelProperty(value = "订单状态")
    private Integer orderStatus;


}
