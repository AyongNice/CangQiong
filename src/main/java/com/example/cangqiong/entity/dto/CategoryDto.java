package com.example.cangqiong.entity.dto;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class CategoryDto {

    private Integer id;

    private String name;
    //sort
    private Integer sort;
    private Integer status;
    //type
    private Integer type;
    private LocalDateTime createTime;
    private Integer createUser;
    private LocalDateTime updateTime;
    private String updateUser;
}
