package com.example.cangqiong.mapper;


import com.example.cangqiong.dto.DishDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishMapper {

 public Integer addDish(DishDto dishDto);
}
