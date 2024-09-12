package com.example.cangqiong.entity.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginVo {
    private String id;

    private String token;

    /**
     * openid
     */
    private String openId;


}
