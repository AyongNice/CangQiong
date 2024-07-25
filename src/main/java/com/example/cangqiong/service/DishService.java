package com.example.cangqiong.service;


import com.example.cangqiong.dto.CategoryDto;
import com.example.cangqiong.dto.DishDto;
import com.example.cangqiong.mapper.DishMapper;
import com.example.cangqiong.vo.PageVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DishService {

    @Autowired
    DishMapper dishMapper;


    /**
     * 增加菜品
     */
    public Integer addDish(DishDto dishDto) {

        dishDto.setCreateTime(LocalDateTime.now());
        dishDto.setUpdateTime(LocalDateTime.now());
        return dishMapper.addDish(dishDto);
    }

    public PageVo<DishDto> page(Integer pageNum, Integer pageSize,String categoryId, String name, String status) {

        PageHelper.startPage(pageNum, pageSize);
        List<DishDto> list = dishMapper.page(categoryId, name, status);

        PageInfo<DishDto> pageInfo = new PageInfo<>(list);


        return new PageVo<>(pageInfo.getTotal(), pageInfo.getList());
    }

    public List<DishDto> list(String categoryId) {

        return  dishMapper.list(categoryId);
    }
}