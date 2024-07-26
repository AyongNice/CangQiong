package com.example.cangqiong.controller.admin;


import com.example.cangqiong.constant.JwtClaims;
import com.example.cangqiong.constant.JwtProperties;
import com.example.cangqiong.dto.SetmealDto;
import com.example.cangqiong.service.SetmealService;
import com.example.cangqiong.utlis.JwtUtil;
import com.example.cangqiong.utlis.Result;
import com.example.cangqiong.vo.PageVo;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.List;

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

        return setmealService.addSetmeal(setmealDto) >0 ? Result.success() : Result.error("修改失败");

    }

    /**
     * 修改套餐
     *
     * @param id
     * @return
     */
    @PutMapping
    public Result<String> editSetmeal(@RequestBody SetmealDto setmealDto, @RequestHeader("Token") String token) {

        Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);

        setmealDto.setUpdateUser(String.valueOf(claims.get(JwtClaims.EMP_ID)));

        return setmealService.editSetmeal(setmealDto) == 1 ? Result.success() : Result.error("修改失败");
    }


    @GetMapping("/{id}")
    public Result<SetmealDto> getSetmeal(@PathVariable String id) {
        return Result.success(setmealService.getSetmeal(id));
    }

    @GetMapping("/page")
    public Result<PageVo<SetmealDto>> page(@RequestParam(defaultValue = "1") Integer page,
                                           @RequestParam(defaultValue = "10") Integer pageSize,
                                           @Param("mame") String name,
                                           @Param("status") String status,
                                           @Param("categoryId") String categoryId
    ) {
        return Result.success(setmealService.page(page, pageSize, name, status, categoryId));
    }


}
