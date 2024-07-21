package com.example.cangqiong.service;


import com.example.cangqiong.constant.JwtClaims;
import com.example.cangqiong.constant.JwtProperties;
import com.example.cangqiong.dto.LongDto;
import com.example.cangqiong.mapper.Login;
import com.example.cangqiong.utlis.JwtUtil;
import com.example.cangqiong.utlis.PasswordUtil;
import com.example.cangqiong.vo.EmployeeLoginVO;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;


import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService {


    @Autowired
    private Login login;

    @Autowired
    private StringRedisTemplate strRiesT;

    @Qualifier("jwtProperties")
    @Autowired
    JwtProperties jwtProperties;

    public EmployeeLoginVO checkPassword(LongDto loginDto) {

        LongDto date = login.getPassword(loginDto);

        Boolean chack = PasswordUtil.checkPassword(loginDto.getPassword(), date.getPassword());

        if (chack) {
            Map<String, Object> calmins = new HashMap<>();
            calmins.put(JwtClaims.EMP_ID, date.getId());

            String token = JwtUtil.createJWT(jwtProperties.getAdminSecretKey(), jwtProperties.getAdminTtl(), calmins);

            // 设置过期时间为 20 分钟
            Duration duration = Duration.ofMinutes(20);
            //存储redistribution
            strRiesT.opsForValue().set("token:" + date.getId(), token, duration);


            return EmployeeLoginVO.builder().id(date.getId()).token(token).name(date.getName()).userName(date.getUsername()).build();
        }


        return null;

    }
}
