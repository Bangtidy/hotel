package com.scau.ruan.hotel.entity.VO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data

public class RegisterIn implements Serializable {

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

}
