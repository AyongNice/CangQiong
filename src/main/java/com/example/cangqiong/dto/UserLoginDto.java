package com.example.cangqiong.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 小程序登录Dto
 */

@Data
public class UserLoginDto {

    private String username;

    private String location;

    private String phone;
    /**
     * openid
     */
    private String openId;
    /**
     * 头像
     */
    private String avatar;

    /**
     * 身份证号
     */
    private Integer idNumber;
    /**
     * 性别
     */
    private String sex;

    private LocalDateTime createTime;
}
