package com.scau.ruan.hotel.service;

import com.scau.ruan.hotel.entity.RoomType;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bangtidy
 * @since 2021-05-13
 */
public interface RoomTypeService extends IService<RoomType> {

    boolean addRoomType(RoomType roomType);
}
