package com.example.cangqiong.mapper;


import com.example.cangqiong.dto.CategoryDto;
import org.apache.ibatis.annotations.Mapper;


/**
 * 菜品分类
 */
@Mapper
public interface CategoryMapper {

    public Integer addCategory(CategoryDto categoryDto);
}
