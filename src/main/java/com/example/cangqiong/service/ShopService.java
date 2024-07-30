package com.example.cangqiong.service;


import com.example.cangqiong.constant.JwtClaims;
import com.example.cangqiong.constant.JwtProperties;
import com.example.cangqiong.utlis.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class ShopService {

    @Autowired
    private StringRedisTemplate strRidesT;

    // RedisTemplate
    @Qualifier("jwtProperties")
    @Autowired
    JwtProperties jwtProperties;


    @Autowired
    JwtUtil jwtUtil;

    /**
     * 获取商铺状态
     *
     * @param id 商铺id
     * @return value 店铺状态 1 营业 0 休息
     */
    //获取有效期
    public Integer getShopStatus(String id) {

        String value = strRidesT.opsForValue().get(JwtClaims.STORE_ADMIN + id);

        return value != null ? Integer.parseInt(value) : 0;

    }


    /**
     * 设置有效期
     *
     * @param id     商铺id
     * @param status 店铺状态
     */
    public void setShopStatus(String token, String status) {


        strRidesT.opsForValue().set(JwtClaims.STORE_ADMIN + jwtUtil.getID(token), status);
    }
}
