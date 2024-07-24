package com.example.cangqiong.service;


import com.example.cangqiong.dto.CategoryDto;
import com.example.cangqiong.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryService {


    @Autowired
    CategoryMapper categoryMapper;

    public Integer addCategory(CategoryDto categoryDto) {

        categoryDto.setStatus(1);
        categoryDto.setCreateTime(LocalDateTime.now());
        categoryDto.setUpdateTime(LocalDateTime.now());

        return categoryMapper.addCategory(categoryDto);
    }

    public List<CategoryDto> list(Integer type) {

        return categoryMapper.list(type);
    }
}
