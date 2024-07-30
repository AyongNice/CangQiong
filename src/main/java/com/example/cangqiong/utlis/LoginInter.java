package com.example.cangqiong.utlis;


import com.example.cangqiong.config.AuthenticationHeaderStrategy;
import com.example.cangqiong.config.AuthenticationStrategy;
import com.example.cangqiong.config.TokenAuthenticationStrategy;
import com.example.cangqiong.constant.JwtClaims;
import com.example.cangqiong.constant.JwtProperties;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 拦截器登陆校验
 */


@Slf4j
public class LoginInter implements HandlerInterceptor {

    private JwtProperties jwtProperties;


    //管理端 token
    private AuthenticationStrategy tokenAuthenticationStrategy;


    //小程序 Authentica
    private AuthenticationStrategy authenticationHeaderStrategy;


    public LoginInter(JwtProperties jwtProperties, StringRedisTemplate strRidesT) {

        this.jwtProperties = jwtProperties;
        this.tokenAuthenticationStrategy = new TokenAuthenticationStrategy(jwtProperties, strRidesT);

        this.authenticationHeaderStrategy = new AuthenticationHeaderStrategy(jwtProperties, strRidesT);

    }

    /**
     * 前置拦截
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //获取请求头token
        String token = request.getHeader("token");

        String authentication = request.getHeader("authentication");

        if (token == null && authentication == null) return false;

        String tokenValue = token != null ? token : authentication;

        //解析token
        Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), tokenValue);

        if (token != null) {
            return tokenAuthenticationStrategy.authenticate(response, tokenValue, claims);
        }

        return authenticationHeaderStrategy.authenticate(response, tokenValue, claims);


    }


    /**
     * 后置拦截
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
