package com.example.cangqiong.entity.dto;


import lombok.Data;

@Data
public class CartDto {

    /**
     * dishFlavor	string	非必须		口味
     * dishId	integer	非必须		菜品id
     * format: int64
     * <p>
     * setmealId	integer	非必须		套餐id
     */
    private String dishFlavorId;
    private String dishId;
    private String setmealId;

    private String name;

    // 用户id
    private String userId;
    // 店铺id
    private String storeId;

}
