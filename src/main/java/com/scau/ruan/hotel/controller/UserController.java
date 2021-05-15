package com.scau.ruan.hotel.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.scau.ruan.hotel.entity.User;
import com.scau.ruan.hotel.service.UserService;
import com.scau.ruan.hotel.util.Result;
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
@RequestMapping("/hotel/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    //登录
    @PostMapping("login")
    public Result login(@RequestBody User user){
        return userService.login(user);
    }


}

