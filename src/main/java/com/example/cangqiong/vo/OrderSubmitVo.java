package com.example.cangqiong.vo;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderSubmitVo {
    /**
     * ─ id	integer	必须		订单id
     * format: int64
     * <p>
     * ├─ orderAmount	number	必须		订单金额
     * ├─ orderNumber	string	必须		订单号
     * ├─ orderTime	string	必须		下单时间
     */
    private String id;
    private String orderNumber;
    private BigDecimal orderAmount;
    private LocalDateTime orderTime;

}
