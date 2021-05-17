package com.scau.ruan.hotel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.scau.ruan.hotel.entity.OrderDetail;
import com.scau.ruan.hotel.mapper.OrderDetailMapper;
import com.scau.ruan.hotel.service.OrderDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bangtidy
 * @since 2021-05-13
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

    @Override
    public List<OrderDetail> getOrderDetailByOrderId(Integer orderId) {
        QueryWrapper<OrderDetail> wrapper = new QueryWrapper<>();
        wrapper.eq("order_id",orderId);
        List<OrderDetail> orderDetails = baseMapper.selectList(wrapper);
        return orderDetails;
    }
}
