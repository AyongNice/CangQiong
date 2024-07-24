package com.example.cangqiong.controller.admin;


import com.example.cangqiong.dto.DishDto;
import com.example.cangqiong.mapper.DishMapper;
import com.example.cangqiong.service.DishService;
import com.example.cangqiong.utlis.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 菜品管理
 */
@RestController
@RequestMapping("/admin/dish")
@Slf4j
public class DishController {

    @Autowired
    DishService dishService;

    /**
     * 添加菜品
     */

    public Result addDish(@RequestBody DishDto dishDto) {

        return dishService.addDish(dishDto) == 1 ? Result.success() : Result.error("添加失败");
    }

}
