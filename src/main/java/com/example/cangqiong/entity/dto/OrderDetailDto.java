package com.example.cangqiong.entity.dto;

import java.math.BigDecimal;

public class OrderDetailDto {

    /**
     *     name        varchar(32)    null comment '名字',
     *     image       varchar(255)   null comment '图片',
     *     order_id    bigint         not null comment '订单id',
     *     dish_id     bigint         null comment '菜品id',
     *     setmeal_id  bigint         null comment '套餐id',
     *     dish_flavor varchar(50)    null comment '口味',
     *     number      int default 1  not null comment '数量',
     *     amount      decimal(10, 2) not null comment '金额',
     *     store_id    int default 1  not null
     */
    private String name;
    private String image;
    private String orderId;
    private String dishId;
    private String setmealId;
    private String dishFlavor;
    private Integer number;
    private BigDecimal amount;
    private Integer storeId;
}
