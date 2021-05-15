package com.scau.ruan.hotel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.scau.ruan.hotel.entity.User;
import com.scau.ruan.hotel.mapper.UserMapper;
import com.scau.ruan.hotel.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scau.ruan.hotel.util.Result;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    //登录
    @Override
    public Result login(User user) {
        String email = user.getEmail();
        String password = user.getUserPassword();
       /* email = "117@qq.com";
        password = "123456";*/
        //根据邮箱查找用户
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("email",email);
        User user1 = baseMapper.selectOne(wrapper);

        if(user1 == null){
            return Result.error().message("该用户不存在");
        }

        //判断密码
        if(!password.equals(user1.getUserPassword())){
            return Result.error().message("登录失败");
        }

        return Result.ok();
    }
}
