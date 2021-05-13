package com.scau.ruan.hotel.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2021-05-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="OrderDetail对象", description="")
public class OrderDetail implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "明细id")
    @TableId(value = "detail_id", type = IdType.AUTO)
    private Integer detailId;

    @ApiModelProperty(value = "订单id")
    private Integer orderId;

    @ApiModelProperty(value = "房间号")
    private Integer roomId;

    @ApiModelProperty(value = "小计")
    private BigDecimal subAmount;

    @ApiModelProperty(value = "预付款额")
    private BigDecimal advance;


}
