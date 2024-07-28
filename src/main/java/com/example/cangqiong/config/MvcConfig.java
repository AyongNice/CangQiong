package com.example.cangqiong.config;


import com.example.cangqiong.constant.JwtProperties;
import com.example.cangqiong.utlis.LoginInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@EnableAspectJAutoProxy
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Qualifier("jwtProperties")
    @Autowired
    JwtProperties jwtProperties;

    @Autowired
    private StringRedisTemplate strRidesT;



    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInter(jwtProperties,strRidesT)).excludePathPatterns("/admin/employee/login");
    }


}
