package com.example.cangqiong.mapper;

import com.example.cangqiong.dto.SetmealDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface SetmealMapper {


    @Insert("insert into setmeal(category_id, description, image, name, price, status, create_time, create_user, update_time, update_user) " +
            "values(#{categoryId}, #{description}, #{image}, #{name}, #{price}, #{status}, #{createTime}, #{createUser}, #{updateTime}, #{updateUser})"
    )
    public Integer addSetmeal(SetmealDto setmealDto);
}
