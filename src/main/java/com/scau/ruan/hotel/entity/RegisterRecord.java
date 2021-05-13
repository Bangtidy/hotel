package com.scau.ruan.hotel.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
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
@ApiModel(value="RegisterRecord对象", description="")
public class RegisterRecord implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "登记id")
    @TableId(value = "register_id", type = IdType.AUTO)
    private Integer registerId;

    @ApiModelProperty(value = "明细id")
    private Integer detailId;

    @ApiModelProperty(value = "顾客身份证号码")
    private String customerId;

    @ApiModelProperty(value = "顾客姓名")
    private String customerName;

    @ApiModelProperty(value = "顾客联系方式")
    private String customerPhone;

    @ApiModelProperty(value = "入住时间")
    private Date registerTime;

    @ApiModelProperty(value = "退房时间")
    private Date checkoutTime;


}
