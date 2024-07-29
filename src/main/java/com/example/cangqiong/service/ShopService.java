package com.example.cangqiong.service;


import com.example.cangqiong.constant.JwtClaims;
import com.example.cangqiong.constant.JwtProperties;
import com.example.cangqiong.mapper.ShopMapper;
import com.example.cangqiong.utlis.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ShopService {

    @Autowired
    private StringRedisTemplate strRidesT;

    // RedisTemplate
    @Qualifier("jwtProperties")
    @Autowired
    JwtProperties jwtProperties;

    /**
     * 获取商铺状态
     *
     * @param id 商铺id
     * @return value 店铺状态 1 营业 0 休息
     */
    //获取有效期
    public Integer getShopStatus(String id) {

        return Integer.valueOf(Objects.requireNonNull(strRidesT.opsForValue().get(JwtClaims.STORE_ADMIN + id)));
    }


    /**
     * 设置有效期
     *
     * @param id     商铺id
     * @param status 店铺状态
     */
    public void setShopStatus(String token, String status) {

        //解析令牌获取id
        Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);

        strRidesT.opsForValue().set(JwtClaims.STORE_ADMIN + claims.get(JwtClaims.EMP_ID), status);
    }
}
