package com.example.cangqiong.entity.dto;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class PaymentDto {

    /**
     * 名称	类型	是否必须	默认值	备注	其他信息
     * orderNumber	string	必须		订单号
     * payMethod	integer	必须		支付方式
     * format: int32
     */
    private String orderNumber;
    private Integer payMethod;
    //订单状态
    private String status;
    private LocalDateTime checkoutTime;
    //支付状态
    private String payStatus;

}
