package com.example.cangqiong.dto;


import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class DishDto {


    private String id;
    private String name;
    private Integer categoryId;
    private Integer price;
    private String image;
    private String description;
    private String status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String createUser;
    private String updateUser;
    private String categoryName;
    //口味
    private List<Flavor> flavors;


}
