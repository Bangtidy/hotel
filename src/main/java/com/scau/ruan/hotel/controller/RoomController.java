package com.scau.ruan.hotel.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.scau.ruan.hotel.entity.Room;
import com.scau.ruan.hotel.service.RoomService;
import com.scau.ruan.hotel.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bangtidy
 * @since 2021-05-13
 */
@RestController
@RequestMapping("/hotel/room")
@CrossOrigin
public class RoomController {

    @Autowired
    private RoomService roomService;
    //添加客房
    @PostMapping("addroom")
    public Result addroom(@RequestBody Room room){
        boolean flag = roomService.addroom(room);
        if (flag){
            return Result.ok();
        }else{
            return Result.error().message("添加失败，检查该房间是否存在");
        }
    }
    //获取全部客房
    @GetMapping("getAllRoom")
    public Result getAllRoom(){
        List<Room> list = roomService.list();
        return Result.ok().data("list",list);
    }

    //根据客房类型获取客房
    @GetMapping("getRoomByTypeId/{typeId}")
    public Result getRoomByTypeId(@PathVariable int typeId){
        List<Room> list = roomService.getRoomByTypeId(typeId);
        return Result.ok().data("list",list);
    }


    //删除客房
    @PostMapping("deleteRoom/{roomId}")
    public Result delelteRoom(@PathVariable int roomId){
        boolean flag = roomService.removeById(roomId);
        if(flag){
            return Result.ok();
        }else{
            return Result.error().message("删除失败，可能该客房已不存在");
        }
    }

    //修改客房
    @PostMapping("updateRoom")
    public Result updateRoom(@RequestBody Room room){
        boolean flag = roomService.updateById(room);
        if(flag){
            return Result.ok();
        }else{
            return Result.error().message("删除失败，可能该客房已不存在");
        }
    }

}

