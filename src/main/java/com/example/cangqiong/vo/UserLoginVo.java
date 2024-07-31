package com.example.cangqiong.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
