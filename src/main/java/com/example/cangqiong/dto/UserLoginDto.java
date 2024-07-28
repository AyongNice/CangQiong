package com.example.cangqiong.dto;

import lombok.Data;

/**
 * 小程序登录Dto
 */

@Data
public class UserLoginDto {

    private String username;

    private String cloudID;

    private String  location;
}
