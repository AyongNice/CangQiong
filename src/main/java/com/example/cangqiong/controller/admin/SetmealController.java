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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/setmeal")
@Slf4j
public class SetmealController {

    @Autowired
    private SetmealService setmealService;


    @Qualifier("jwtProperties")
    @Autowired
    JwtProperties jwtProperties;


    /**
     * 套餐新增
     *
     * @param setmealDto
     * @param token
     * @return
     */

    @PostMapping
    public Result<String> addSetmeal(@RequestBody SetmealDto setmealDto, @RequestHeader("Token") String token) {

        Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);

        setmealDto.setCreateUser(String.valueOf(claims.get(JwtClaims.EMP_ID)));

        return setmealService.addSetmeal(setmealDto) > 0 ? Result.success() : Result.error("修改失败");

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


    /**
     * 套餐根据id查询
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<SetmealDto> getSetmeal(@PathVariable String id) {
        return Result.success(setmealService.getSetmeal(id));
    }


    //分页
    @GetMapping("/page")
    public Result<PageVo<SetmealDto>> page(@RequestParam(defaultValue = "1") Integer page,
                                           @RequestParam(defaultValue = "10") Integer pageSize,
                                           @Param("mame") String name,
                                           @Param("status") String status,
                                           @Param("categoryId") String categoryId
    ) {
        return Result.success(setmealService.page(page, pageSize, name, status, categoryId));
    }

    @DeleteMapping
    public Result<String> deleteSetmeal(@RequestParam String ids) {
        //将ids字符串转换为字符串集合List
        String[] split = ids.split(",");

        return setmealService.deleteSetmeal(split) > 0 ? Result.success() : Result.error("删除失败");
    }

    @PostMapping("/status/{status}")
    public Result<String> editStatus(@PathVariable String status, @Param("id") String id) {


        return setmealService.editStatus(status, id) > 0 ? Result.success() : Result.error("修改失败");
    }


}
