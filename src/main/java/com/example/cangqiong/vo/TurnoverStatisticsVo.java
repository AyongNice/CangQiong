package com.example.cangqiong.vo;


import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 营业额统计
 */
@Data
public class TurnoverStatisticsVo {


    private String dateList;
    private String turnoverList;


    /**
     * ├─ newUserList	string	必须		新增用户数列表，以逗号分隔
     * ├─ totalUserList	string	必须		总用户量列表，以逗号分隔
     */

    private String totalUserList;
    private String newUserList;

    /**
     * ├─ orderCompletionRate	number	必须		订单完成率
     * format: double
     *
     * ├─ orderCountList	string	必须		订单数列表，以逗号分隔
     * ├─ totalOrderCount	integer	必须		订单总数
     * format: int32
     *
     * ├─ validOrderCount	integer	必须		有效订单数
     * format: int32
     *
     * ├─ validOrderCountList	string	必须		有效订单数列表，以逗号分隔
     */

    private String orderCompletionRate;
    private String totalOrderCount;
    private String orderCountList;
    private String validOrderCount;
    private String validOrderCountList;


    /**
     * ├─ nameList	string	必须		商品名称列表，以逗号分隔
     * ├─ numberList	string	必须		销量列表，以逗号分隔
     */

    private String nameList;
    private String numberList;



}
