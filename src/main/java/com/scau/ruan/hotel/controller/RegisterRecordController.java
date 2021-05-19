package com.scau.ruan.hotel.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.scau.ruan.hotel.entity.RegisterRecord;
import com.scau.ruan.hotel.entity.VO.RegisterIn;
import com.scau.ruan.hotel.service.RegisterRecordService;
import com.scau.ruan.hotel.util.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bangtidy
 * @since 2021-05-13
 */
@RestController
@RequestMapping("/hotel/register-record")
@CrossOrigin
public class RegisterRecordController {

    @Autowired
    private RegisterRecordService registerRecordService;

    //登记入住
    @PostMapping("registerIn")
    public Result registerIn(@RequestBody RegisterIn registerIn){

        boolean flag = registerRecordService.regisiterIn(registerIn);
        if(flag){
            return Result.ok();
        }else{
            return Result.error();
        }
    }


    //退房
    @PostMapping("registerOut/{registerRecordId}")
    public Result registerOut(@PathVariable Integer registerRecordId){
        RegisterRecord registerRecord = registerRecordService.regisiterOut(registerRecordId);
        return Result.ok().data("registerRecord",registerRecord);
    }

}

