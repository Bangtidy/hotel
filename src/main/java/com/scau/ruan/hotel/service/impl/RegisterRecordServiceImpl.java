package com.scau.ruan.hotel.service.impl;

import com.scau.ruan.hotel.entity.*;
import com.scau.ruan.hotel.entity.VO.RegisterIn;
import com.scau.ruan.hotel.mapper.RegisterRecordMapper;
import com.scau.ruan.hotel.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scau.ruan.hotel.util.DateUtil;
import io.swagger.models.auth.In;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bangtidy
 * @since 2021-05-13
 */
@Service
public class RegisterRecordServiceImpl extends ServiceImpl<RegisterRecordMapper, RegisterRecord> implements RegisterRecordService {

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private HOrderService orderService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomTypeService roomTypeService;

    //登记入住
    @Override
    public boolean regisiterIn(RegisterIn registerIn) {
        RegisterRecord registerRecord = new RegisterRecord();
        BeanUtils.copyProperties(registerIn,registerRecord);
        int insert = baseMapper.insert(registerRecord);
        return insert==1;
    }

    //退房
    @Override
    public RegisterRecord regisiterOut(Integer registerRecordId) {
        RegisterRecord registerRecord = baseMapper.selectById(registerRecordId);
        //设置退房时间
        registerRecord.setCheckoutTime(new Date());

        //获取时间差
        Integer distanceTimes = DateUtil.getDistanceTimes(registerRecord.getRegisterTime(), registerRecord.getCheckoutTime());

        //先根据订单明细id获取订单明细，再根据订单明细中的房间号获取房间，再根据房间的类型id获取房间类型，再根据房间类型获取价格
        Integer detailId = registerRecord.getDetailId();
        OrderDetail orderDetail = orderDetailService.getById(detailId);



        Integer roomId = orderDetail.getRoomId();
        Room room = roomService.getById(roomId);
        room.setRoomStatus(0);//将房间状态设置为0
        Integer typeId = room.getTypeId();
        RoomType roomType = roomTypeService.getById(typeId);
        BigDecimal price = roomType.getPrice();
        Integer subAmount =price.intValue() * distanceTimes;
        //为订单详情赋金额
        orderDetail.setSubAmount(new BigDecimal(subAmount));

        //获取订单号
          Integer orderId = orderDetail.getOrderId();
        HOrder order = orderService.getById(orderId);

        //将金额加到订单中
        order.setAmount(order.getAmount().add(new BigDecimal(subAmount)));

        //检查该订单的所有房间是否都退订
        Boolean flag = orderService.checkFinsh(order);
        //订单已完成
        if(flag){
            order.setPayStatus(1);//已付款
            order.setOrderStatus(1);//已完成
        }

        //保存订单详情和登记单和订单
        orderDetailService.updateById(orderDetail);
        baseMapper.updateById(registerRecord);
        orderService.updateById(order);


        return registerRecord;
    }

}
