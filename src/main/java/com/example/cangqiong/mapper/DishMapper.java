package com.example.cangqiong.mapper;


import com.example.cangqiong.dto.DishDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishMapper {

    public Integer addDish(DishDto dishDto);

    public List<DishDto> page(String categoryId, String name, String status);


    @Select("select * from dish where category_id = #{categoryId}")
    public List<DishDto> list(String categoryId);
}
