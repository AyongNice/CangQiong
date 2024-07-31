package com.example.cangqiong.config;


import com.example.cangqiong.constant.JwtClaims;
import com.example.cangqiong.constant.JwtProperties;
import com.example.cangqiong.utlis.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 小程序用户端验证拦截
 */
public class AuthenticationHeaderStrategy implements AuthenticationStrategy {
    private JwtProperties jwtProperties;
    private StringRedisTemplate strRidesT;

    public AuthenticationHeaderStrategy(JwtProperties jwtProperties, StringRedisTemplate strRidesT) {
        this.jwtProperties = jwtProperties;
        this.strRidesT = strRidesT;
    }

    @Override
    public boolean authenticate(HttpServletResponse response, String tokenValue, Claims claims) {

        //不存在token返回401
        if (strRidesT.opsForValue().get(JwtClaims.KOKENKEY + claims.get(JwtClaims.OPEN_ID)) == null) {
            response.setStatus(401);
            return false;
        }
        //存在就刷新token时间有效期
        strRidesT.opsForValue().set(JwtClaims.KOKENKEY + claims.get(JwtClaims.OPEN_ID), tokenValue, jwtProperties.getAdminTtl());

        //Redis校验是否存在
        return true;
    }
}
