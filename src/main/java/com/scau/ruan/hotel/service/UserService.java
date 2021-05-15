package com.scau.ruan.hotel.service;

import com.scau.ruan.hotel.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scau.ruan.hotel.util.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bangtidy
 * @since 2021-05-13
 */
public interface UserService extends IService<User> {

    Result login(User user);

    Result register(String userName,String password,String passwordAgain,String email);

    int addUser(User user);

    int addUser(String userName,String password,String email,String activeCode);

    Result activate(String code);

    int update(User user);
}
