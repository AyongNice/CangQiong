package com.example.cangqiong.entity.vo;

import lombok.Data;

/**
 * 查询今日运营数据
 * businessData
 */
@Data
public class BusinessDataVo {
    /**
     * ├─ newUsers	integer	必须		新增用户数
     * format: int32
     *
     * ├─ orderCompletionRate	number	必须		订单完成率
     * format: double
     *
     * ├─ turnover	number	必须		营业额
     * format: double
     *
     * ├─ unitPrice	number	必须		平均客单价
     * format: double
     *
     * ├─ validOrderCount	integer	必须		有效订单数
     * format: int32
     */
    private Integer newUsers;
    private Double orderCompletionRate;
    private Double turnover;
    private Double unitPrice;
    private Integer validOrderCount;
}
