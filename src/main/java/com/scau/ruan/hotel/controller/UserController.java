package com.scau.ruan.hotel.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.scau.ruan.hotel.entity.User;
import com.scau.ruan.hotel.service.UserService;
import com.scau.ruan.hotel.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;
import java.util.Random;

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
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    //登录
    @PostMapping("login")
    public Result login(@RequestBody User user){
        return userService.login(user);
    }


    @GetMapping("register")
    public String goRegisterPage(){
        return "register";
    }


    @PostMapping("register")
    public Result index(@RequestBody Map<String, String> form){

//        @RequestParam("userName") String userName,
//        @RequestParam("password") String password,
//        @RequestParam("passwordAgain") String passwordAgain,
//        @RequestParam("email") String email
        String userName = form.get("userName");
        String password = form.get("password");
        String passwordAgain = form.get("passwordAgain");
        String email = form.get("email");
            return userService.register(userName,password,passwordAgain,email);
    }

    @PostMapping("checkEmail")
    public Result checkEmail(@RequestParam("email") String email,
                        Model model){
        return userService.checkEmail(email);
    }

    @GetMapping("activate")
    public Result activate(@RequestParam(value = "code") String code){

        return userService.activate(code);
    }
}

