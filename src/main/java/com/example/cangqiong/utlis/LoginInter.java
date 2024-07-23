package com.example.cangqiong.utlis;


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
    private StringRedisTemplate strRidesT;

    public LoginInter(JwtProperties jwtProperties, StringRedisTemplate strRidesT) {
        this.jwtProperties = jwtProperties;
        this.strRidesT = strRidesT;
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

        if (token == null) return false;

        //解析token
        Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);
        log.info("claims" +"   "+ strRidesT.opsForValue().get(JwtClaims.KOKENKEY + claims.get(JwtClaims.EMP_ID)));
        //不存在token返回401
        if (strRidesT.opsForValue().get(JwtClaims.KOKENKEY + claims.get(JwtClaims.EMP_ID)) == null) {
            response.setStatus(401);
            return false;
        }
        //存在就刷新token时间有效期
        strRidesT.opsForValue().set("token:" + claims.getId(), token, jwtProperties.getAdminTtl());

        //Redis校验是否存在
        return true;

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
