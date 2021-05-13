package com.scau.ruan.hotel.service.impl;

import com.scau.ruan.hotel.entity.Order;
import com.scau.ruan.hotel.mapper.OrderMapper;
import com.scau.ruan.hotel.service.OrderService;
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
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
