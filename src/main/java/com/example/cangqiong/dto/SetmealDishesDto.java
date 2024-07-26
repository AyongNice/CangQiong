package com.example.cangqiong.dto;


import lombok.Data;

/**
 * 套餐菜品关联表
 */
@Data
public class SetmealDishesDto {
    private Integer id;
    private String setmealId;
    private Integer dishId;
    private String name;
    private Integer copies;
    private Integer price;
}
