package com.scau.ruan.hotel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.scau.ruan.hotel.entity.Room;
import com.scau.ruan.hotel.mapper.RoomMapper;
import com.scau.ruan.hotel.service.RoomService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bangtidy
 * @since 2021-05-13
 */
@Service
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements RoomService {

    //添加客房
    @Override
    public boolean addroom(Room room) {
        Room r = baseMapper.selectById(room.getRoomId());
        if(r != null){//该房间已存在
            return false;
        }
        room.setRoomStatus(0);
        int insert = baseMapper.insert(room);
        return insert==1;
    }

    //根据客房类型获取客房
    @Override
    public List<Room> getRoomByTypeId(int typeId) {
        QueryWrapper<Room> wrapper = new QueryWrapper<>();
        wrapper.eq("type_id",typeId);
        List<Room> list = baseMapper.selectList(wrapper);
        return list;
    }


}
