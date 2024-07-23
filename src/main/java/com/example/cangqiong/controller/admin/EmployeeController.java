package com.example.cangqiong.controller.admin;


import com.example.cangqiong.constant.JwtClaims;
import com.example.cangqiong.constant.JwtProperties;
import com.example.cangqiong.dto.LongDto;
import com.example.cangqiong.dto.User;

import com.example.cangqiong.service.LoginService;

import com.example.cangqiong.utlis.JwtUtil;
import com.example.cangqiong.utlis.Result;
import com.example.cangqiong.vo.EmployeeLoginVO;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/employee")
@Slf4j
public class EmployeeController {


    @Autowired
    private LoginService loginService;


    @Qualifier("jwtProperties")
    @Autowired
    JwtProperties jwtProperties;

    @PostMapping("/login")
    public Result login(@RequestBody LongDto loginDto) {

        EmployeeLoginVO employeeLoginVO = loginService.checkPassword(loginDto);

        return employeeLoginVO == null ? Result.error("密码错误") : Result.success(employeeLoginVO);

    }

    /**
     * 新增员工
     */

    @PostMapping
    public Result addEmployee(@RequestBody User user, @RequestHeader("Token") String token) {

        Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);
        user.setCreateUser((String) claims.get(JwtClaims.EMP_ID));

        return loginService.addEmployee(user) == 1 ? Result.success() : Result.error("修改失败");
    }


}
