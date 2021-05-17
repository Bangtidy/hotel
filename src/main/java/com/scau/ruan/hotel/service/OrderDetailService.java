package com.scau.ruan.hotel.service;

import com.scau.ruan.hotel.entity.OrderDetail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bangtidy
 * @since 2021-05-13
 */
public interface OrderDetailService extends IService<OrderDetail> {

    List<OrderDetail> getOrderDetailByOrderId(Integer orderId);
}
