package com.example.cangqiong.controller.admin;


import com.example.cangqiong.constant.JwtProperties;
import com.example.cangqiong.entity.dto.DishDto;
import com.example.cangqiong.service.admin.DishService;
import com.example.cangqiong.utlis.JwtUtil;
import com.example.cangqiong.utlis.Result;
import com.example.cangqiong.entity.vo.PageVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜品管理
 */

@Slf4j
@RestController
@RequestMapping("/admin/dish")
public class DishController {

    @Autowired
    DishService dishService;


    @Qualifier("jwtProperties")
    @Autowired
    JwtProperties jwtProperties;


    @Autowired
    JwtUtil jwtUtil;


    /**
     * 添加菜品
     */


    @PostMapping
    public Result<String> addDish(@RequestBody DishDto dishDto, @RequestHeader("Token") String token) {

        return dishService.addDish(dishDto, token) == 1 ? Result.success() : Result.error("添加失败");
    }


    /**
     * 修改菜品
     */
    @PutMapping
    public Result<String> editDish(@RequestBody DishDto dishDto, @RequestHeader("Token") String token) {


        return dishService.editDish(dishDto, token) == 1 ? Result.success() : Result.error("修改失败");
    }


    /**
     * 查询分页
     */

    @GetMapping("/page")
    public Result<PageVo<DishDto>> page(@RequestParam(defaultValue = "1") Integer page,
                                        @RequestParam(defaultValue = "10") Integer pageSize,
                                        @Param("name") String name, String status,
                                        @Param("categoryId") String categoryId,
                                        @RequestHeader("Token") String token
                                        ) {
        return Result.success(dishService.page(page, pageSize, categoryId, name, status,jwtUtil.getID(token)));
    }


    /**
     * 查询菜品
     */
    @GetMapping("/list")
    public Result<List<DishDto>> list(@Param("categoryId") String categoryId, @RequestHeader("Token") String token) {

        return Result.success(dishService.list(categoryId, jwtUtil.getID(token)));

    }

    /**
     * 根据id查询菜品
     */
    @GetMapping("/{id}")
    public Result<DishDto> getDishById(@PathVariable String id, @RequestHeader("Token") String token) {
        return Result.success(dishService.getDishById(id, jwtUtil.getID(token)));
    }

    /**
     * 更改菜品状态
     */
    @PostMapping("/status/{status}")
    public Result<String> editStatus(@PathVariable String status, @Param("id") String id, @RequestHeader("Token") String token) {
        return dishService.editStatus(status, id, token) == 1 ? Result.success() : Result.error("修改失败");
    }

    @DeleteMapping
    public Result<String> deleteDish(String ids, @RequestHeader("Token") String token) {
        return dishService.deleteDish(ids, token) > 0 ? Result.success() : Result.error("删除失败");
    }

}
