package com.example.cangqiong.controller.user;


import com.example.cangqiong.dto.DishDto;
import com.example.cangqiong.service.DishService;
import com.example.cangqiong.utlis.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user/dish")
public class UserDishController {

    @Autowired
    DishService dishService;
    //查询菜品

    @GetMapping("/list")
    public Result<List<DishDto>> list(@Param("categoryId") String categoryId,@Param("storeId") String storeId) {
        return Result.success(dishService.list(categoryId,storeId));
    }

}
