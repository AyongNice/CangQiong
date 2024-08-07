package com.example.cangqiong.service;


import com.example.cangqiong.constant.JwtClaims;
import com.example.cangqiong.constant.TakeOrders;
import com.example.cangqiong.dto.OrderAdminDto;
import com.example.cangqiong.dto.OrderSubmit;
import com.example.cangqiong.dto.TakeOrdersDto;
import com.example.cangqiong.mapper.OrderAdminMapper;
import com.example.cangqiong.utlis.JwtUtil;
import com.example.cangqiong.vo.CartVo;
import com.example.cangqiong.vo.PageVo;
import com.example.cangqiong.vo.StatisticsVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;


/**
 * 获取订单管理端 service
 */
@Service
public class OrderService {

    @Autowired
    private OrderAdminMapper orderAdminMapper;

    @Autowired
    JwtUtil jwtUtil;

    /**
     * 获取订单
     *
     * @param orderAdminDto
     * @param token
     * @return
     */

    public PageVo<OrderSubmit> getOrderPage(OrderAdminDto orderAdminDto, String token) {
        orderAdminDto.setStoreId(jwtUtil.getID(token));
        PageHelper.startPage(orderAdminDto.getPage(), orderAdminDto.getPageSize());

        List<OrderSubmit> list = orderAdminMapper.getOrderPage(orderAdminDto);

        PageInfo<OrderSubmit> pageInfo = new PageInfo<>(list);

        return new PageVo<>(pageInfo.getTotal(), pageInfo.getList());
    }


    /**
     * 获取订单详情
     *
     * @param orderId
     * @param token
     * @return
     */
    public OrderSubmit getOrderDetails(String orderId, String token) {
        List<CartVo> cartVoList = orderAdminMapper.getOrderCartVoDetails(orderId);
        OrderSubmit orderSubmit = orderAdminMapper.getOrderDetails(orderId, jwtUtil.getID(token));
        orderSubmit.setOrderDetailList(cartVoList);
        return orderSubmit;
    }

    /**
     * 操作订单 接单 巨蛋
     *
     * @param orderId
     * @param token
     */
    public void manipulateOrders(TakeOrdersDto takeOrdersDto, String token) {
        takeOrdersDto.setStoreId(jwtUtil.getID(token));
        orderAdminMapper.manipulateOrders(takeOrdersDto);
    }

    /**
     * 订单哦统计
     * @param token
     * @return
     */

    //获取TakeOrders 整个对象的code

    public StatisticsVo getOrderStatistics(String token) {
        return orderAdminMapper.getOrderStatistics(jwtUtil.getID(token),
                TakeOrders.DELIVERING.getCode(),
                TakeOrders.WAIT_ACCEPT.getCode(),
                TakeOrders.ACCEPTED.getCode(),
                TakeOrders.CANCELED.getCode(),
                TakeOrders.COMPLETED.getCode());
    }
}
