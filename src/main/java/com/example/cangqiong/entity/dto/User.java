package com.example.cangqiong.entity.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {

    private String name;
    private String username;
    private String phone;
    private String idNumber;
    private String sex;
    private String password;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String createUser;
    private String updateUser;

    //总数
    private  Integer count;
}
