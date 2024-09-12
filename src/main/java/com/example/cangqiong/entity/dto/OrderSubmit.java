package com.example.cangqiong.entity.dto;


import com.example.cangqiong.entity.vo.CartVo;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class OrderSubmit {
    /**
     * ddressBookId	integer	必须		地址簿id
     * format: int64
     * <p>
     * amount	number	必须		总金额
     * deliveryStatus	integer	必须		配送状态：  1立即送出  0选择具体时间
     * format: int32
     * <p>
     * estimatedDeliveryTime	string	必须		预计送达时间
     * packAmount	integer	必须		打包费
     * format: int32
     * <p>
     * payMethod	integer	必须		付款方式
     * format: int32
     * <p>
     * remark	string	必须		备注
     * tablewareNumber	integer	必须		餐具数量
     * format: int32
     * <p>
     * tablewareStatus	integer	必须		餐具数量状态  1按餐量提供  0选择具体数量
     * format: int32
     * status                  int        default 1 not null comment '订单状态 1待付款 2待接单 3已接单 4派送中 5已完成 6已取消 7退款',
     * order_time              datetime             not null comment '下单时间',
     * <p>
     * pay_status              tinyint    default 0 not null comment '支付状态 0未支付 1已支付 2退款',
     */

    private String id;
    private String number;
    private String address;
    private Integer addressBookId;
    private Integer amount;
    private Integer deliveryStatus;
    private String estimatedDeliveryTime;
    private Integer packAmount;
    private Integer payMethod;
    private String remark;
    private Integer tablewareNumber;
    private Integer tablewareStatus;
    private String userId;
    private String storeId;
    private Integer status;
    private LocalDateTime orderTime;
    private Integer payStatus;
    private String cancelReason;
    private String cancelTime;
    private String checkoutTime;
    private String phone;
    private String consignee;

    private String dishIds;
    private List<String> orderDishes;


    /**
     * 将sql 字符串转换为list
     *
     * @param dishIdsString
     * @param dishIdsString
     */
    void setOrderDishes(String dishIdsString) {
        if (dishIdsString != null && !dishIdsString.isEmpty()) {
            this.orderDishes = Arrays.asList(dishIdsString.split(","));
        } else {
            this.orderDishes = new ArrayList<>();
        }
    }

    private  List<CartVo> orderDetailList;



}
