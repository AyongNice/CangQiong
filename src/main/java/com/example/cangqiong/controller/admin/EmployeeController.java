package com.example.cangqiong.controller.admin;


import com.example.cangqiong.dto.LongDto;
import com.example.cangqiong.mapper.Login;
import com.example.cangqiong.service.LoginService;
import com.example.cangqiong.utlis.PasswordUtil;
import com.example.cangqiong.utlis.Result;
import com.example.cangqiong.vo.EmployeeLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/employee")
@Slf4j
public class EmployeeController {


    @Autowired
    private LoginService loginService;


    @PostMapping("/login")
    public Result login(@RequestBody LongDto loginDto) {

        EmployeeLoginVO employeeLoginVO = loginService.checkPassword(loginDto);

        return employeeLoginVO == null ? Result.error("密码错误") : Result.success(employeeLoginVO);

    }


}
