package com.example.cangqiong.vo;


import lombok.Data;


/**
 * 套餐统计
 */
@Data
public class SetmealsSum {

    //已停售套餐数量
    private Integer discontinued;
    //已启售套餐数量
    private Integer sold;
}
