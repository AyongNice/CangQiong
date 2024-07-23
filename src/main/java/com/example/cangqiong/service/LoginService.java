package com.example.cangqiong.service;


import com.example.cangqiong.constant.JwtClaims;
import com.example.cangqiong.constant.JwtProperties;
import com.example.cangqiong.constant.UserConst;
import com.example.cangqiong.dto.LongDto;
import com.example.cangqiong.dto.User;
import com.example.cangqiong.mapper.Admin;

import com.example.cangqiong.utlis.JwtUtil;
import com.example.cangqiong.utlis.PasswordUtil;
import com.example.cangqiong.vo.EmployeeLoginVO;

import com.example.cangqiong.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;


import java.time.Duration;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginService {


    @Autowired
    private Admin admin;

    @Autowired
    private StringRedisTemplate strRiesT;

    @Qualifier("jwtProperties")
    @Autowired
    JwtProperties jwtProperties;


    public EmployeeLoginVO checkPassword(LongDto loginDto) {

        LongDto date = admin.getPassword(loginDto);

        boolean chack = PasswordUtil.checkPassword(loginDto.getPassword(), date.getPassword());

        if (chack) {
            Map<String, Object> calmins = new HashMap<>();
            calmins.put(JwtClaims.EMP_ID, date.getId());

            String token = JwtUtil.createJWT(jwtProperties.getAdminSecretKey(), jwtProperties.getAdminTtl(), calmins);

            // 设置过期时间为 20 分钟
            Duration duration = Duration.ofMinutes(10);
            //存储redistribution
            strRiesT.opsForValue().set(JwtClaims.KOKENKEY + date.getId(), token, duration);


            return EmployeeLoginVO.builder().id(date.getId()).token(token).name(date.getName()).userName(date.getUsername()).build();
        }


        return null;

    }


    /**
     * 新增员工
     * @param user
     * @return
     */

    public Integer addEmployee(User user) {

        user.setStatus(1);
        user.setPassword(PasswordUtil.encryptPassword(UserConst.password));

        user.setCreateTime(LocalDateTime.now());

        user.setUpdateTime(LocalDateTime.now());

        return admin.addEmployee(user);


    }

    /**
     * 分页查询
     */

    public PageVo page(Integer pageNum, Integer pageSize,String name) {

        List<User> list =  admin.page(pageNum, pageSize, name);
        PageVo pageVo = new PageVo();
        pageVo.setRecords(list);
        User user =   list.get(0);
        pageVo.setTotal(user.getCount());
        return  pageVo;
    }

}
