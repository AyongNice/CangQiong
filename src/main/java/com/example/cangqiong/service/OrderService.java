package com.example.cangqiong.service;


import com.example.cangqiong.dto.OrderAdminDto;
import com.example.cangqiong.dto.OrderSubmit;
import com.example.cangqiong.mapper.OrderAdminMapper;
import com.example.cangqiong.utlis.JwtUtil;
import com.example.cangqiong.vo.PageVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


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
}
