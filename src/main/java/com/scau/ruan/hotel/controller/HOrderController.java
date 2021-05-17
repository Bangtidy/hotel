package com.scau.ruan.hotel.controller;

import com.scau.ruan.hotel.entity.VO.OrderInfo;
import com.scau.ruan.hotel.service.HOrderService;
import com.scau.ruan.hotel.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bangtidy
 * @since 2021-05-16
 */
@RestController
@RequestMapping("/hotel/h-order")
@CrossOrigin
public class HOrderController {

    @Autowired
    private HOrderService hOrderService;

    //添加订单
    @PostMapping("addOrder")
    public Result addOrder(@RequestBody OrderInfo orderInfo){
        Result result = hOrderService.addOrder(orderInfo);
        return result;
    }

    //根据id删除订单
    @PostMapping("deleteOrder/{orderId}")
    public Result deleteOrder(@PathVariable int orderId){
        boolean flag = hOrderService.deleteOrderById(orderId);
        if(flag){
            return Result.ok();
        }else{
            return Result.error();
        }
    }

    //获取全部订单
    @GetMapping("getAllOrder")
    public Result getAllOrder(){
        List<OrderInfo> list = hOrderService.getAllOrder();
        return Result.ok().data("list",list);
    }

    //根据id修改订单
    @PostMapping("updateOrderById")
    public Result updateOrder(@RequestBody OrderInfo orderInfo){
        boolean flag = hOrderService.updateOrderInfoById(orderInfo);
        if(flag){
            return Result.ok();
        }else{
            return Result.error();
        }
    }
}

