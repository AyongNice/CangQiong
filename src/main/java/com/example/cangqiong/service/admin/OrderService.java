package com.example.cangqiong.service.admin;


import com.example.cangqiong.constant.TakeOrders;
import com.example.cangqiong.entity.dto.OrderAdminDto;
import com.example.cangqiong.entity.dto.OrderSubmit;
import com.example.cangqiong.entity.dto.TakeOrdersDto;
import com.example.cangqiong.mapper.OrderAdminMapper;
import com.example.cangqiong.utlis.JwtUtil;
import com.example.cangqiong.entity.vo.BusinessDataVo;
import com.example.cangqiong.entity.vo.CartVo;
import com.example.cangqiong.entity.vo.PageVo;
import com.example.cangqiong.entity.vo.StatisticsVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /**
     * 查询今日运营数据
     * businessData
     */
    public BusinessDataVo getBusinessData(String token) {
        BusinessDataVo businessDataVo = orderAdminMapper.getBusinessData(jwtUtil.getID(token));


        Map<String, Long> baifenbi = orderAdminMapper.baifenbi(jwtUtil.getID(token));
        Map<String, Long> zongshu = orderAdminMapper.zongshu(jwtUtil.getID(token));
        Double orderCompletionRate = 0.0;
        if (baifenbi != null && zongshu != null) {
            orderCompletionRate = Double.valueOf(baifenbi.get("finish")) / Double.valueOf(zongshu.get("total"));
            businessDataVo.setOrderCompletionRate(Double.valueOf(orderCompletionRate));
        }
        return businessDataVo;
    }
}
