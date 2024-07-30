package com.example.cangqiong.config;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 * 认证策略 模式
 */
public interface AuthenticationStrategy {
    /**
     * 认证
     *
     * @param response   响应
     * @param tokenValue 令牌
     * @param claims     声明
     * @return
     */
    boolean authenticate(HttpServletResponse response, String tokenValue, Claims claims);

}
