package com.scau.ruan.hotel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.scau.ruan.hotel.entity.RoomType;
import com.scau.ruan.hotel.mapper.RoomTypeMapper;
import com.scau.ruan.hotel.service.RoomTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bangtidy
 * @since 2021-05-13
 */
@Service
public class RoomTypeServiceImpl extends ServiceImpl<RoomTypeMapper, RoomType> implements RoomTypeService {

    @Override
    public boolean addRoomType(RoomType roomType) {
        Integer typeId = roomType.getTypeId();
        QueryWrapper<RoomType> wrapper = new QueryWrapper<>();
        wrapper.eq("type_id",typeId);
        RoomType rt = baseMapper.selectOne(wrapper);
        if(rt != null){
            return false;
        }
        int insert = baseMapper.insert(roomType);
        return  insert==1;
    }
}
