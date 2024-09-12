package com.example.cangqiong.entity.vo;


import lombok.Data;

@Data
public class StatisticsVo {
    /**
     * ├─ confirmed	integer	必须		待派送数量
     * format: int32
     *
     * ├─ deliveryInProgress	integer	必须		派送中数量
     * format: int32
     *
     * ├─ toBeConfirmed	integer	必须		待接单数量
     * format: int32
     * ├─ allOrders	integer	必须		全部订单
     * format: int32
     *
     * ├─ cancelledOrders	integer	必须		已取消数量
     * format: int32
     *
     * ├─ completedOrders	integer	必须		已完成数量
     * format: int32
     *
     * ├─ deliveredOrders	integer	必须		待派送数量
     * format: int32
     *
     * ├─ waitingOrders	integer	必须		待接单数量
     */
    private Integer confirmed;
    private Integer deliveredOrders;
    private Integer toBeConfirmed;

    private Integer allOrders;
    private Integer cancelledOrders;
    private Integer completedOrders;
    private Integer waitingOrders;

}
