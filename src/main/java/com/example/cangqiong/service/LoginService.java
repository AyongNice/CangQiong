package com.example.cangqiong.service;


import com.example.cangqiong.constant.JwtClaims;
import com.example.cangqiong.constant.JwtProperties;
import com.example.cangqiong.constant.UserConst;
import com.example.cangqiong.dto.EditPasswordDto;
import com.example.cangqiong.dto.LongDto;
import com.example.cangqiong.dto.User;
import com.example.cangqiong.mapper.Admin;

import com.example.cangqiong.utlis.JwtUtil;
import com.example.cangqiong.utlis.PasswordUtil;
import com.example.cangqiong.vo.EmployeeLoginVO;

import com.example.cangqiong.vo.PageVo;
import io.jsonwebtoken.Claims;
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

        if (PasswordUtil.checkPassword(loginDto.getPassword(), date.getPassword())) {
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
     * 推出登陆
     */
    public void logout(Claims claims) {
        strRiesT.delete(JwtClaims.KOKENKEY + claims.get(JwtClaims.EMP_ID));
    }

    /**
     * 新增员工
     *
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
     * 查询员工
     */

    public User getEmployeeById(String id) {

        return admin.getEmployeeById(id);

    }

    /**
     * 修改员工信息
     */

    public Integer editEmployee(User user) {
        user.setUpdateTime(LocalDateTime.now());
        user.setStatus(1);
        return admin.editEmployee(user);
    }

    /**
     * 分页查询
     */

    public PageVo page(Integer pageNum, Integer pageSize, String name) {
        Integer start = (pageNum - 1) * pageSize;
        List<User> list = admin.page(start, pageSize, name);

        Integer count = admin.count(name);
        PageVo pageVo = new PageVo();
        pageVo.setRecords(list);

        pageVo.setTotal(count);
        return pageVo;
    }


    /**
     * 修改密码
     *
     * @param editPasswordDto
     * @return
     */
    public Integer editPassword(EditPasswordDto editPasswordDto) {

        String oldPassword = PasswordUtil.encryptPassword(editPasswordDto.getOldPassword());

        editPasswordDto.setNewPassword(PasswordUtil.encryptPassword(editPasswordDto.getNewPassword()));

        if (admin.getPasswordById(editPasswordDto.getEmpId()).equals(oldPassword)) {
            return admin.editPassword(editPasswordDto);
        }

        return 0;


    }


}
