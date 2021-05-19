package com.scau.ruan.hotel.service;

import com.scau.ruan.hotel.entity.Room;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scau.ruan.hotel.util.Result;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bangtidy
 * @since 2021-05-13
 */
public interface RoomService extends IService<Room> {

    boolean addroom(Room room);


    List<Room> getRoomByTypeId(int typeId);

    Result getTypeStatistics();

}
