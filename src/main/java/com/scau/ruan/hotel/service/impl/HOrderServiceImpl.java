package com.scau.ruan.hotel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.scau.ruan.hotel.entity.HOrder;
import com.scau.ruan.hotel.entity.OrderDetail;
import com.scau.ruan.hotel.entity.Room;
import com.scau.ruan.hotel.entity.VO.OrderInfo;
import com.scau.ruan.hotel.mapper.HOrderMapper;
import com.scau.ruan.hotel.service.HOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scau.ruan.hotel.service.OrderDetailService;
import com.scau.ruan.hotel.service.RoomService;
import com.scau.ruan.hotel.util.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bangtidy
 * @since 2021-05-16
 */
@Service
@Transactional
public class HOrderServiceImpl extends ServiceImpl<HOrderMapper, HOrder> implements HOrderService {

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private RoomService roomService;

    //添加订单
    @Override
    //开启事务
    @Transactional
    public Result addOrder(OrderInfo orderInfo) {

        orderInfo.setAmount(new BigDecimal(0));//总金额
        orderInfo.setPayStatus(0);//付款状态
        orderInfo.setOrderStatus(0);//订单状态

        //将Info对象转化为HOrder对象
        HOrder hOrder = new HOrder();
        BeanUtils.copyProperties(orderInfo,hOrder);
        int insert = baseMapper.insert(hOrder);
        //事务测试
        //int test = 1/0;
        if(insert == 0){
            //添加失败
            return Result.error();
        }
        //获取该订单号
        Integer orderId = hOrder.getOrderId();

        //根据房间类型和数量订房
        List<HashMap<String, Integer>> list = orderInfo.getList();
        for(HashMap<String,Integer> map : list){
            Integer typeId = map.get("typeId");
            Integer num = map.get("num");
            for(int i=0;i<num;i++){
                List<Room> roomList = roomService.getRoomByTypeId(typeId);
                if(roomList.size()==0) return Result.error().message("找不到该房间类型");
                //将状态不是0的房间移除
                int roomNum = roomList.size();
                for(Room room : roomList){
                    if(room.getRoomStatus().intValue()!=0) {
                       roomNum--;
                    }
                }

                if(roomNum<num) return Result.error().message("所定房间不足");

                for(Room room:roomList){
                    if(room.getRoomStatus().intValue()==0){
                        if(num == 0) break;
                        room.setRoomStatus(1);//将房间状态设置为1
                        roomService.updateById(room);
                        OrderDetail orderDetail = new OrderDetail();
                        orderDetail.setRoomId(room.getRoomId());
                        orderDetail.setOrderId(orderId);
                        orderDetail.setSubAmount(new BigDecimal(0));
                        orderDetail.setAdvance(new BigDecimal(0));
                        orderDetailService.save(orderDetail);
                        num--;//需要房数减一
                    }
                }
            }
        }

        return Result.ok().data("orderId",orderId);
    }

    //删除订单并删除订单对应的订单详情
    @Override
    public boolean deleteOrderById(int orderId) {
        QueryWrapper<OrderDetail> wrapper = new QueryWrapper<>();
        wrapper.eq("order_id",orderId);
        orderDetailService.remove(wrapper);

        int flag = baseMapper.deleteById(orderId);
        if(flag == 0) return false;
        return true;
    }

    //获取全部订单
    @Override
    public List<OrderInfo> getAllOrder() {
        //结果集
        List<OrderInfo> res = new ArrayList<>();

        //先获取订单
        List<HOrder> hOrders = baseMapper.selectList(null);
        //再根据订单id获取订单详情
        //并将订单详情绑定到对应的订单上
        for(HOrder order:hOrders){
            Integer orderId = order.getOrderId();
            List<OrderDetail> list = orderDetailService.getOrderDetailByOrderId(orderId);
            OrderDetail[] orderDetails = list.toArray(new OrderDetail[list.size()]);//转化为数组

            //绑定数据
            OrderInfo orderInfo = new OrderInfo();
            BeanUtils.copyProperties(order,orderInfo);
            orderInfo.setOrderDetails(orderDetails);

            //保存到结果集
            res.add(orderInfo);
        }
        return res;
    }

    @Override
    public boolean updateOrderInfoById(OrderInfo orderInfo) {
        //将Info对象转化为HOrder对象
        HOrder hOrder = new HOrder();
        BeanUtils.copyProperties(orderInfo,hOrder);
        int i = baseMapper.updateById(hOrder);
        if(i == 0){
            //修改失败
            return false;
        }
        //获取该订单号
        //Integer orderId = hOrder.getOrderId();

        //获取订单详情数组
        OrderDetail[] orderDetails = orderInfo.getOrderDetails();
        for(OrderDetail od : orderDetails){
            //修改订单详情
            orderDetailService.updateById(od);
        }
        return true;
    }

    //检查该订单是否已完成
    @Override
    public Boolean checkFinsh(HOrder order) {
        List<OrderDetail> orderDetailList = orderDetailService.getOrderDetailByOrderId(order.getOrderId());
        for(OrderDetail orderDetail:orderDetailList){
            if(orderDetail.getSubAmount().intValue() == 0){
                //存在房间没有退房
                return false;
            }
        }
        return true;
    }
}
