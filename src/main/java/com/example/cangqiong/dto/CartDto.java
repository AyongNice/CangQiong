package com.example.cangqiong.dto;


import lombok.Data;

@Data
public class CartDto {

    /**
     * dishFlavor	string	非必须		口味
     * dishId	integer	非必须		菜品id
     * format: int64
     *
     * setmealId	integer	非必须		套餐id
     */
    private Integer dishFlavorId;
    private Integer dishId;
    private Integer setmealId;

}
