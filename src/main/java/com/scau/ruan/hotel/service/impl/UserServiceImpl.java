package com.scau.ruan.hotel.service.impl;

import com.scau.ruan.hotel.entity.User;
import com.scau.ruan.hotel.mapper.UserMapper;
import com.scau.ruan.hotel.service.UserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
