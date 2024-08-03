package com.example.cangqiong.service;


import com.example.cangqiong.dto.CartDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartsService {

    @Autowired
    private StringRedisTemplate strRedisT;

    public Integer add(CartDto cartDto) {


        return  1;
    }
}
