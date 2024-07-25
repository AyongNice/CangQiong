package com.example.cangqiong.mapper;

import com.example.cangqiong.dto.SetmealDishesDto;
import com.example.cangqiong.dto.SetmealDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface SetmealMapper {


    //    @Insert("insert into setmeal(category_id, description, image, name, price, status, create_time, create_user, update_time, update_user) " +
//            "values(#{categoryId}, #{description}, #{image}, #{name}, #{price}, #{status}, #{createTime}, #{createUser}, #{updateTime}, #{updateUser})"
//    )
    public Integer addSetmeal(SetmealDto setmealDto);

    /**
     * 套餐菜品关联表
     * @param name
     * @param status
     * @param categoryId
     * @return
     */
    //批量插入
    public Integer addSetmealDishes(List<SetmealDishesDto> list);


    public List<SetmealDto> page(String name, String status, String categoryId);

    @Select("select name,price,category_id as categoryId ,description,image from setmeal")
    SetmealDto getSetmeal(Integer id);
}
