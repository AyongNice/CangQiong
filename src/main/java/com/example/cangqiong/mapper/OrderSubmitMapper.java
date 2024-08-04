package com.example.cangqiong.mapper;

import com.example.cangqiong.dto.OrderSubmit;
import com.example.cangqiong.dto.PaymentDto;
import com.example.cangqiong.vo.CartVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface OrderSubmitMapper {


    /**
     * 用户提交订单
     *
     * @param orderSubmit
     * @return
     */
    public Integer addOrderSubmit(OrderSubmit orderSubmit);


    /**
     * 支付
     *
     * @param map
     */
    public void payment(PaymentDto paymentDto);

    void addDetail(List<CartVo> list);
}
