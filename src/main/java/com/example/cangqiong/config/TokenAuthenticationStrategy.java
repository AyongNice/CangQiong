package com.example.cangqiong.config;


import com.example.cangqiong.constant.JwtClaims;
import com.example.cangqiong.constant.JwtProperties;
import com.example.cangqiong.utlis.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 管理端 token验证方式
 */
public class TokenAuthenticationStrategy implements AuthenticationStrategy {


    private JwtProperties jwtProperties;
    private StringRedisTemplate strRidesT;

    public TokenAuthenticationStrategy(JwtProperties jwtProperties, StringRedisTemplate strRidesT) {
        this.jwtProperties = jwtProperties;
        this.strRidesT = strRidesT;
    }


    @Override
    public boolean authenticate(HttpServletResponse response, String tokenValue, Claims claims) {

        //不存在token返回401
        if (strRidesT.opsForValue().get(JwtClaims.KOKENKEY + claims.get(JwtClaims.EMP_ID)) == null) {
            response.setStatus(401);
            return false;
        }
        //存在就刷新token时间有效期
        strRidesT.opsForValue().set(JwtClaims.KOKENKEY + claims.getId(), tokenValue, jwtProperties.getAdminTtl());

        //Redis校验是否存在
        return true;
    }
}
