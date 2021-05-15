package com.scau.ruan.hotel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.scau.ruan.hotel.async.MailTask;
import com.scau.ruan.hotel.config.EmailConfig;
import com.scau.ruan.hotel.entity.User;
import com.scau.ruan.hotel.enums.UserTypeEnums;
import com.scau.ruan.hotel.mapper.UserMapper;
import com.scau.ruan.hotel.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scau.ruan.hotel.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

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

    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Autowired
    private EmailConfig emailConfig;

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

    @Override
    public Result register(String userName,String password,String passwordAgain,String email) {

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("email",email);
        User dbUser = baseMapper.selectOne(wrapper);


        if(dbUser== null) {
            if(password.equals(passwordAgain)) {
                //code = 当前时间 + 位随机数
                String activeCode = String.valueOf(new Date().getTime()) + String.valueOf(new Random().nextInt(900) + 100);

                taskExecutor.execute(new MailTask(activeCode + password, email, emailConfig.getMailMailFrom(), emailConfig.getMailDomainName(), javaMailSender));

                addUser(userName,password,email,activeCode);

                return Result.success200("注册成功,赶快打开邮箱去激活吧");
            }else {
                return Result.error().message("msg_passwordError,密码不一致!");
            }

        }else {
            return Result.error().message("msg_registered,该邮箱已被注册!");
        }
    }

    @Override
    public int addUser(User user) {
        return baseMapper.insert(user);
    }

    @Override
    public int addUser(String userName, String password, String email,String code) {
        User user = new User();
        user.setUserName(userName);
        user.setUserPassword(code+password);
        user.setEmail(email);
        user.setUserType(UserTypeEnums.UNACTIVATE_USER.getCode());//用户未激活,用户类型为0(未知)
        return addUser(user);
    }

    @Override
    public Result activate(String code) {
        //  if(System.currentTimeMillis()-Long.valueOf(code.substring(0,code.length()-3))/1000<3){

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_password",code);
        User dbUser = baseMapper.selectOne(wrapper);

        System.out.println(dbUser);
        //如果用户不等于null，把用户类型修改为普通用户
        if (dbUser !=null){
            dbUser.setUserType(UserTypeEnums.ORDINARY_USER.getCode());
            //把code验证码清空，已经不需要了
            dbUser.setUserPassword(code.substring(16));
            System.out.println(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format( new Date()) +"INFO "+ dbUser);
            update(dbUser);
            return Result.success200("activate_error,激活成功");
        }
        // }
        return Result.error().message("activate_error,激活失败");
    }

    @Override
    public int update(User user) {
        return baseMapper.updateById(user);
    }


}
