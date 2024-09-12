package com.example.cangqiong.mapper;

import com.example.cangqiong.entity.dto.OrderSubmit;
import com.example.cangqiong.entity.dto.PaymentDto;
import com.example.cangqiong.entity.vo.CartVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


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
