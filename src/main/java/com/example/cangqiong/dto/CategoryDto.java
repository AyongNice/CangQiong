package com.example.cangqiong.dto;

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
    private String createUser;
    private LocalDateTime updateTime;
    private String updateUser;
}
