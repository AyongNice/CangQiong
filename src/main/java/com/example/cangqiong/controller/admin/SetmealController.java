package com.example.cangqiong.controller.admin;


import com.example.cangqiong.constant.JwtClaims;
import com.example.cangqiong.constant.JwtProperties;
import com.example.cangqiong.dto.SetmealDto;
import com.example.cangqiong.service.SetmealService;
import com.example.cangqiong.utlis.JwtUtil;
import com.example.cangqiong.utlis.Result;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/setmeal")
@Slf4j
public class SetmealController {

    @Autowired
    private SetmealService setmealService;


    @Qualifier("jwtProperties")
    @Autowired
    JwtProperties jwtProperties;

    @PostMapping
    public Result<String> addSetmeal(@RequestBody SetmealDto setmealDto, @RequestHeader("Token") String token) {

        Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);

        setmealDto.setCreateUser(String.valueOf(claims.get(JwtClaims.EMP_ID)));

        return setmealService.addSetmeal(setmealDto) == 1 ? Result.success() : Result.error("添加失败");

    }


}
