package com.example.cangqiong.mapper;


import com.example.cangqiong.dto.CategoryDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


/**
 * 菜品分类
 */
@Mapper
public interface CategoryMapper {

    public Integer addCategory(CategoryDto categoryDto);



    public List<CategoryDto> list(Integer type);

    public List<CategoryDto> page(String name, String type);


    @Update("update category set status = #{status} where id = #{id}")
    public Integer editStatus(String status, Integer id);


    @Update("update category set name = #{name}, type =  #{type}, sort = #{sort} where id = #{id}")
    public Integer editCategory(CategoryDto categoryDto);


    @Delete("delete from category where id = #{id}")
    Integer deleteCategory(Integer id);
}
