package com.example.cangqiong.mapper;


import com.example.cangqiong.dto.CategoryDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * 菜品分类
 */
@Mapper
public interface CategoryMapper {

    public Integer addCategory(CategoryDto categoryDto);


    @Select("select * from category where type = #{type}")
    List<CategoryDto> list(Integer type);

    List<CategoryDto> page(String name, String type);
}
