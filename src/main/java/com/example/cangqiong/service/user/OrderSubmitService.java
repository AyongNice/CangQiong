package com.example.cangqiong.service.user;


import cn.hutool.core.util.IdUtil;
import com.example.cangqiong.entity.dto.OrderSubmit;
import com.example.cangqiong.entity.dto.PaymentDto;
import com.example.cangqiong.mapper.OrderSubmitMapper;
import com.example.cangqiong.utlis.JwtUtil;
import com.example.cangqiong.entity.vo.CartVo;
import com.example.cangqiong.entity.vo.OrderSubmitVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户订单管理
 */

@Service
public class OrderSubmitService {


    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private OrderSubmitMapper orderSubmitMapper;


    @Autowired
    private ShoppingCartsService shoppingCartsService;

    /**
     * 提交订单
     *
     * @param orderSubmit
     * @param authentication
     * @return
     */
    public OrderSubmitVo submit(OrderSubmit orderSubmit, String authentication) {


        //雪花id
        String id = IdUtil.getSnowflakeNextIdStr();

        LocalDateTime orderTime = LocalDateTime.now();

        orderSubmit.setId(id);
        //订单id
        orderSubmit.setNumber(id);
        orderSubmit.setUserId(jwtUtil.getOpenId(authentication));
        orderSubmit.setStatus(1);
        orderSubmit.setOrderTime(orderTime);
        orderSubmit.setPayStatus(0);
        orderSubmitMapper.addOrderSubmit(orderSubmit);


        /**
         * 获取购物车添加的订单菜品详情
         */
        List<CartVo> list = shoppingCartsService.getShoppingCartList(orderSubmit.getStoreId(), authentication);
        list.forEach(cartVo -> {
            cartVo.setOrderId(id);
            cartVo.setStoreId(orderSubmit.getStoreId());
        });
        orderSubmitMapper.addDetail(list);

        /**
         * 清空当前用户所在店铺购物车
         */
        shoppingCartsService.cleanShoppingCart(orderSubmit.getStoreId(), authentication);

        OrderSubmitVo orderSubmitVo = new OrderSubmitVo();
        orderSubmitVo.setId(id);
        orderSubmitVo.setOrderNumber(id);
        orderSubmitVo.setOrderAmount(new BigDecimal(orderSubmit.getAmount()));
        orderSubmitVo.setOrderTime(orderTime);

        return orderSubmitVo;

    }


    /**
     * 支付
     *
     * @param map
     */
    public Map<String, LocalDateTime> payment(PaymentDto paymentDto) {
        paymentDto.setPayStatus("1");
        paymentDto.setCheckoutTime(LocalDateTime.now());
        paymentDto.setStatus("2");
        orderSubmitMapper.payment(paymentDto);
        //当前时间 + 30分钟
        Map<String, LocalDateTime> map = new HashMap<>();
        map.put("estimatedDeliveryTime", LocalDateTime.now().plusMinutes(30));

        return map;
    }
}
