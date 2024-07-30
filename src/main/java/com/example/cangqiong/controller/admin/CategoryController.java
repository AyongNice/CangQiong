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

    @Autowired
    JwtUtil jwtUtil;

    /**
     * 新增分类
     */


    @PostMapping
    public Result<String> addCategory(@RequestBody CategoryDto categoryDto, @RequestHeader("Token") String token) {


        return categoryService.addCategory(categoryDto,token) > 0 ? Result.success() : Result.error("新增失败");
    }

    /**
     * 修改分类
     *
     * @param type
     * @return
     */
    @PutMapping

    public Result<String> editCategory(@RequestBody CategoryDto categoryDto, @RequestHeader("Token") String token) {

        return categoryService.editCategory(categoryDto, token) > 0 ? Result.success() : Result.error("修改失败");
    }

    @DeleteMapping
    public Result<String> deleteCategory(@Param("id") Integer id, @RequestHeader("Token") String token) {
        return categoryService.deleteCategory(id,token) > 0 ? Result.success() : Result.error("删除失败");
    }


    /**
     * 根据type查询分类
     */

    @GetMapping("/list")
    public Result<List<CategoryDto>> list(@Param("type") Integer type, @RequestHeader("Token") String token) {

        return Result.success(categoryService.list(type, jwtUtil.getID(token)));
    }

    @GetMapping("/page")
    public Result<PageVo<CategoryDto>> page(@RequestParam(defaultValue = "1") Integer page,
                                            @RequestParam(defaultValue = "10") Integer pageSize,
                                            @Param("name") String name, String type,
                                            @RequestHeader("Token") String token) {


        return Result.success(categoryService.page(page, pageSize, name, type, jwtUtil.getID(token)));
    }


    /**
     * 修改产品分类
     *
     * @param
     * @return
     */
    @PostMapping("/status/{status}")
    public Result<String> editStatus(@PathVariable String status, @Param("id") Integer id, @RequestHeader("Token") String token) {
        return categoryService.editStatus(status, id,token) > 0 ? Result.success() : Result.error("修改失败");
    }


}
