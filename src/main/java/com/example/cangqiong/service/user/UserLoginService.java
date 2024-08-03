package com.example.cangqiong.service.user;


import com.example.cangqiong.constant.JwtClaims;
import com.example.cangqiong.constant.JwtProperties;
import com.example.cangqiong.dto.UserLoginDto;
import com.example.cangqiong.mapper.StoreMapper;
import com.example.cangqiong.mapper.UserLoginMapper;
import com.example.cangqiong.service.ShopService;
import com.example.cangqiong.utlis.JwtUtil;
import com.example.cangqiong.utlis.Result;
import com.example.cangqiong.vo.StoreInfo;
import com.example.cangqiong.vo.UserLoginVo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserLoginService {

    @Qualifier("jwtProperties")
    @Autowired
    JwtProperties jwtProperties;

    @Autowired
    private StringRedisTemplate strRiesT;


    @Autowired
    private StoreMapper storeMapper;

    @Autowired
    private ShopService shopService;

    @Autowired
    UserLoginMapper userLoginMapper;


    /**
     * 获取openId 从wx官网
     *
     * @param code
     * @return
     */
    public String getOpenId(String code) {


        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(JwtClaims.getRequestUrl(code), String.class);
        // 解析 JSON 响应
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> result;

        try {
            result = mapper.readValue(response, new TypeReference<>() {
            });
            return result.get("openid");

        } catch (Exception e) {
            log.error("Error parsing JSON response: {}", e.getMessage());
            return null;
        }
    }


    public UserLoginVo login(UserLoginDto userLoginDto) {


        String openId = getOpenId(userLoginDto.getCode());

        if (openId == null) {
            // 处理错误情况抛出异常
            throw new RuntimeException("Error getting openid");

        }

        userLoginDto.setOpenId(openId);

        Map<String, Object> calming = new HashMap<>();
        calming.put(JwtClaims.OPEN_ID, userLoginDto.getOpenId());
        String token = JwtUtil.createJWT(jwtProperties.getAdminSecretKey(), jwtProperties.getAdminTtl(), calming);
        // 设置过期时间为 20 分钟
        Duration duration = Duration.ofMinutes(20);
        //存储redistribution
        strRiesT.opsForValue().set(JwtClaims.KOKENKEY + userLoginDto.getOpenId(), token, duration);

        UserLoginDto user = userLoginMapper.getUser(userLoginDto.getOpenId());

        if (user == null) {
            userLoginMapper.addUser(userLoginDto);
        }

        return UserLoginVo.builder().id(userLoginDto.getOpenId()).openId(userLoginDto.getOpenId()).token(token).build();

    }


    public List<StoreInfo> getStoreList() {

        List<StoreInfo> storeList = storeMapper.getStoreList();

        storeList.forEach(storeInfo -> {
            storeInfo.setStatus(shopService.getShopStatus(storeInfo.getId()));
        });


        return storeList;
    }
}
