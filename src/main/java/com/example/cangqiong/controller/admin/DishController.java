package com.example.cangqiong.controller.admin;


import com.example.cangqiong.constant.JwtClaims;
import com.example.cangqiong.constant.JwtProperties;
import com.example.cangqiong.dto.DishDto;
import com.example.cangqiong.mapper.DishMapper;
import com.example.cangqiong.service.DishService;
import com.example.cangqiong.utlis.JwtUtil;
import com.example.cangqiong.utlis.Result;
import com.example.cangqiong.vo.PageVo;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜品管理
 */
@RestController
@RequestMapping("/admin/dish")
@Slf4j
public class DishController {

    @Autowired
    DishService dishService;


    @Qualifier("jwtProperties")
    @Autowired
    JwtProperties jwtProperties;

    /**
     * 添加菜品
     */


    @PostMapping
    public Result<String> addDish(@RequestBody DishDto dishDto, @RequestHeader("Token") String token) {
        Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);

        dishDto.setCreateUser(String.valueOf(claims.get(JwtClaims.EMP_ID)));
        return dishService.addDish(dishDto) == 1 ? Result.success() : Result.error("添加失败");
    }


    /**
     * 修改菜品
     */
    @PutMapping
    public Result<String> editDish(@RequestBody DishDto dishDto, @RequestHeader("Token") String token) {
        Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);
        dishDto.setUpdateUser(String.valueOf(claims.get(JwtClaims.EMP_ID)));
        return dishService.editDish(dishDto) == 1 ? Result.success() : Result.error("修改失败");
    }


    /**
     * 查询分页
     */

    @GetMapping("/page")
    public Result<PageVo<DishDto>> page(@RequestParam(defaultValue = "1") Integer page,
                                        @RequestParam(defaultValue = "10") Integer pageSize,
                                        @Param("name") String name, String status, @Param("categoryId") String categoryId) {
        return Result.success(dishService.page(page, pageSize, categoryId, name, status));
    }


    /**
     * 查询菜品
     */
    @GetMapping("/list")
    public Result<List<DishDto>> list(@Param("categoryId") String categoryId) {

        return Result.success(dishService.list(categoryId));

    }

    /**
     * 根据id查询菜品
     */
    @GetMapping("/{id}")
    public Result<DishDto> getDishById(@PathVariable String id) {
        return Result.success(dishService.getDishById(id));
    }

}
