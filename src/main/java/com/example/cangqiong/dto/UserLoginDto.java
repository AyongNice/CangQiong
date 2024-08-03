package com.example.cangqiong.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 小程序登录Dto
 */

@Data
public class UserLoginDto {


    /**
     * 小程序登陆API获取的 code 用于从wx官网 获取 openid
     */
    private String code;

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
