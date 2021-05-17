package com.scau.ruan.hotel.controller;


import com.scau.ruan.hotel.entity.RoomType;
import com.scau.ruan.hotel.service.RoomTypeService;
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
@RequestMapping("/hotel/room-type")
@CrossOrigin
public class RoomTypeController {

    @Autowired
    private RoomTypeService roomTypeService;

    //添加客房类型
    @PostMapping("addRoomType")
    public Result addRoomType(@RequestBody RoomType roomType){
        boolean flag = roomTypeService.addRoomType(roomType);
        if(flag){
            return Result.ok();
        }else{
            return  Result.error();
        }
    }

    //获取全部客房类型
    @GetMapping("getAllRoomType")
    public Result getAllRoom(){
        List<RoomType> list = roomTypeService.list();
        return Result.ok().data("list",list);
    }

    //删除客房类型
    @PostMapping("deleteRoom/{roomId}")
    public Result delelteRoom(@PathVariable int roomId){
        boolean flag = roomTypeService.removeById(roomId);
        if(flag){
            return Result.ok();
        }else{
            return Result.error().message("删除失败，可能该客房类型已不存在");
        }
    }

    //修改客房类型
    @PostMapping("updateRoom")
    public Result updateRoom(@RequestBody RoomType roomType){
        boolean flag = roomTypeService.updateById(roomType);
        if(flag){
            return Result.ok();
        }else{
            return Result.error().message("删除失败，可能该客房已不存在");
        }
    }
}

