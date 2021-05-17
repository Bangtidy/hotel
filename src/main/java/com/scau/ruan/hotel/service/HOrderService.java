package com.scau.ruan.hotel.service;

import com.scau.ruan.hotel.entity.HOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scau.ruan.hotel.entity.VO.OrderInfo;
import com.scau.ruan.hotel.util.Result;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bangtidy
 * @since 2021-05-16
 */
public interface HOrderService extends IService<HOrder> {

    Result addOrder(OrderInfo orderInfo);

    //List<HOrder> getAll();

    boolean deleteOrderById(int orderId);

    List<OrderInfo> getAllOrder();

    boolean updateOrderInfoById(OrderInfo orderInfo);
}
