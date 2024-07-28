package com.example.cangqiong.config;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthenticationStrategy {
    boolean authenticate(HttpServletResponse response, String tokenValue, Claims claims);

}
