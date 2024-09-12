package com.example.cangqiong.controller.admin;


import com.example.cangqiong.constant.JwtClaims;
import com.example.cangqiong.constant.JwtProperties;
import com.example.cangqiong.entity.dto.EditPasswordDto;
import com.example.cangqiong.entity.dto.LongDto;
import com.example.cangqiong.entity.dto.User;

import com.example.cangqiong.service.admin.LoginService;

import com.example.cangqiong.utlis.JwtUtil;
import com.example.cangqiong.utlis.Result;
import com.example.cangqiong.entity.vo.EmployeeLoginVO;

import com.example.cangqiong.entity.vo.PageVo;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * 员工管理
 */
@Slf4j
@RestController
@RequestMapping("/admin/employee")
public class EmployeeController {


    @Autowired
    private LoginService loginService;

    // RedisTemplate
    @Qualifier("jwtProperties")
    @Autowired
    JwtProperties jwtProperties;


    /**
     * 登录
     *
     * @param loginDto
     * @return
     */
    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(@RequestBody LongDto loginDto) {

        EmployeeLoginVO employeeLoginVO = loginService.checkPassword(loginDto);

        return employeeLoginVO == null ? Result.error("密码错误") : Result.success(employeeLoginVO);

    }


    /**
     * 退出登陆
     */

    @GetMapping("/logout")
    public Result<String> logout(@RequestHeader("Token") String token) {

        Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);
        loginService.logout(claims);
        return Result.success();
    }

    /**
     * 新增员工
     */
    @PostMapping
    public Result<String> addEmployee(@RequestBody User user, @RequestHeader("Token") String token) {

        Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);

        user.setCreateUser(String.valueOf(claims.get(JwtClaims.EMP_ID)));

        return loginService.addEmployee(user) == 1 ? Result.success() : Result.error("修改失败");
    }


    /**
     * 分页查询
     *
     * @param pageNum
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public Result<PageVo<User>> addEmployee(@RequestParam(defaultValue = "1") Integer pageNum,
                                      @RequestParam(defaultValue = "10") Integer pageSize,
                                      String name) {

        return Result.success(loginService.page(pageNum, pageSize, name));
    }


    /**
     * 修改密码
     *
     * @param editPasswordDto
     * @param token
     * @return
     */

    @PutMapping("/editPassword")
    public Result<String> editPassword(@RequestBody EditPasswordDto editPasswordDto, @RequestHeader("Token") String token) {


        Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);

        editPasswordDto.setEmpId(String.valueOf(claims.get(JwtClaims.EMP_ID)));
        return loginService.editPassword(editPasswordDto) == 1 ? Result.success() : Result.error("修改失败");
    }

    /**
     * 根据Id查询员工
     */
    @GetMapping("/{id}")
    public Result<User> getEmployeeById(@PathVariable String id) {

        return Result.success(loginService.getEmployeeById(id));
    }


    /**
     * 修改员工
     *
     * @param user
     * @return
     */
    @PutMapping
    public Result<String> editEmployee(@RequestBody User user) {

        return loginService.editEmployee(user) == 1 ? Result.success() : Result.error("修改失败");
    }

}
