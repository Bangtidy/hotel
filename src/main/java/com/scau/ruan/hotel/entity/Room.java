package com.scau.ruan.hotel.entity;

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
@ApiModel(value="Room对象", description="")
public class Room implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "房间号")
    @TableId(value = "room_id", type = IdType.AUTO)
    private Integer roomId;

    @ApiModelProperty(value = "房间类型")
    private Integer typeId;

    @ApiModelProperty(value = "房间状态")
    private Integer roomStatus;


}
