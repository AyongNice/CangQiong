package com.example.cangqiong.vo;


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
     */
    private Integer confirmed;
    private Integer deliveryInProgress;
    private Integer toBeConfirmed;
}
