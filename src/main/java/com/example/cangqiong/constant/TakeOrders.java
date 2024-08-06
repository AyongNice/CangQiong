package com.example.cangqiong.constant;


import lombok.Data;

/**
 * 接单状态
 */

public enum TakeOrders {
    /**
     * '订单状态 1待付款 2待接单 3已接单 4派送中 5已完成 6已取消 7退款',
     */
    WAIT_PAY("1", "待付款"),
    WAIT_ACCEPT("2", "待接单"),
    ACCEPTED("3", "已接单"),
    DELIVERING("4", "派送中"),
    COMPLETED("5", "已完成"),
    CANCELED("6", "已取消"),
    REFUND("7", "退款"),
    REJECT("8","拒绝订单");
    private String code;
    private String desc;

    TakeOrders(String code, String desc) {
       this.code = code;
    }


    public String getCode() {
        return code;
    }
}
