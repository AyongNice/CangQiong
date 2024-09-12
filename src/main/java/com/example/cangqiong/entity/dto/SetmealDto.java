package com.example.cangqiong.entity.dto;

import com.example.cangqiong.utlis.BaseImageEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetmealDto extends BaseImageEntity {


    private String createUser;
    private String description;
    private String image;
    private String name;
    private Integer status;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
    private Integer price;
    private String updateUser;
    private String id;
    private Integer categoryId;
    private String categoryName;
    private String createName;
    private List<SetmealDishesDto> setmealDishes;


}
