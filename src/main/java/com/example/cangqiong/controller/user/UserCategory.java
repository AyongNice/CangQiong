package com.example.cangqiong.controller.user;


import com.example.cangqiong.entity.dto.CategoryDto;
import com.example.cangqiong.service.admin.CategoryService;
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
@RequestMapping("/user/category")
public class UserCategory {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public Result<List<CategoryDto>> getCategoryList(@Param("type") Integer type, @Param("storeId") String storeId) {

        return Result.success(categoryService.list(type,storeId));
    }


}
