package com.example.cangqiong.service.user;


import com.example.cangqiong.constant.JwtClaims;
import com.example.cangqiong.constant.JwtProperties;
import com.example.cangqiong.dto.UserLoginDto;
import com.example.cangqiong.mapper.StoreMapper;
import com.example.cangqiong.service.ShopService;
import com.example.cangqiong.utlis.JwtUtil;
import com.example.cangqiong.vo.StoreInfo;
import com.example.cangqiong.vo.UserLoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
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
    public UserLoginVo login(UserLoginDto userLoginDto) {

        Map<String, Object> calmins = new HashMap<>();
        calmins.put(JwtClaims.CLOUD_ID, userLoginDto.getCloudID());
        String token = JwtUtil.createJWT(jwtProperties.getAdminSecretKey(), jwtProperties.getAdminTtl(), calmins);
        // 设置过期时间为 20 分钟
        Duration duration = Duration.ofMinutes(10);
        //存储redistribution
        strRiesT.opsForValue().set(JwtClaims.KOKENKEY + userLoginDto.getCloudID(), token, duration);
        return UserLoginVo.builder().id(userLoginDto.getCloudID()).openId(userLoginDto.getCloudID()).token(token).build();

    }


    public List<StoreInfo> getStoreList() {

        List<StoreInfo> storeList = storeMapper.getStoreList();

        storeList.forEach(storeInfo -> {
            storeInfo.setStatus(shopService.getShopStatus(storeInfo.getId()));
        });


        return storeList;
    }
}
