package com.example.cangqiong.controller.admin;


import com.example.cangqiong.constant.JwtClaims;
import com.example.cangqiong.constant.JwtProperties;
import com.example.cangqiong.dto.CategoryDto;
import com.example.cangqiong.service.CategoryService;
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
 * 菜品分类接口
 */
@RestController
@RequestMapping("/admin/category")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Qualifier("jwtProperties")
    @Autowired
    JwtProperties jwtProperties;

    /**
     * 新增分类
     */


    @PostMapping
    public Result<String> addCategory(@RequestBody CategoryDto categoryDto, @RequestHeader("Token") String token) {
        Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);

        categoryDto.setCreateUser(String.valueOf(claims.get(JwtClaims.EMP_ID)));

        return categoryService.addCategory(categoryDto) > 0 ? Result.success() : Result.error("新增失败");
    }


    /**
     * 根据type查询分类
     */

    @GetMapping("/list")
    public Result<List<CategoryDto>> list(@Param("type") Integer type) {
        return Result.success(categoryService.list(type));
    }

    @GetMapping("/page")
    public Result<PageVo<CategoryDto>> page(@RequestParam(defaultValue = "1") Integer page,
                                            @RequestParam(defaultValue = "10") Integer pageSize,
                                            String name, String type) {
        return Result.success(categoryService.page(page, pageSize, name, type));
    }


}
