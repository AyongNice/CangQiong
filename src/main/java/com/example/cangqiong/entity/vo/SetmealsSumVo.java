package com.example.cangqiong.entity.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * 套餐 /菜品统计
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetmealsSumVo implements Serializable {

    //已停售数量
    private Integer discontinued;
    //已启售数量
    private Integer sold;
}
