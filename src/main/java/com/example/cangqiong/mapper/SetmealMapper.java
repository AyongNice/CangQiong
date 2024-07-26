package com.example.cangqiong.mapper;

import com.example.cangqiong.dto.DishDto;
import com.example.cangqiong.dto.SetmealDishesDto;
import com.example.cangqiong.dto.SetmealDto;
import com.example.cangqiong.utlis.Result;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


@Mapper
public interface SetmealMapper {


    public Integer addSetmeal(SetmealDto setmealDto);

    /**
     * 套餐菜品关联表
     *
     * @param name
     * @param status
     * @param categoryId
     * @return
     */
    //批量插入
    public Integer addSetmealDishes(List<SetmealDishesDto> list);


    public List<SetmealDto> page(String name, String status, String categoryId);

    @Select("select id, name,price,category_id as categoryId ,description,image from setmeal where id=#{id}")
    public SetmealDto getSetmeal(String id);


    @Select("select id,name,price,copies,dish_id  as  dishId ,setmeal_id  as setmealId from setmeal_dish where setmeal_id=#{setmealId}")
    public List<SetmealDishesDto> getSetmealDishes(String setmealId);


    @Update("update setmeal set name = #{name}, " +
            "category_id = #{categoryId}, " +
            "description = #{description}," +
            " image = #{image}," +
            " price = #{price}, " +
            "status = #{status}," +
            " update_time=#{updateTime} where id = #{id}")
    public Integer editSetmeal(SetmealDto setmealDto);

    /**
     * 根据 setmeal_id 删除原来的套餐关联
     */

    @Delete("delete from setmeal_dish where setmeal_id = #{setmealId}")
    public Integer deleteSetmealDishes(String setmealId);


    /**
     * 多条删除套餐关联表
     *
     * @param ids
     * @return
     */

    public Integer deleteSetmealDishesList(String[] ids);


    public Integer deleteSetmeal(String[] ids);


    @Update("update setmeal set status = #{status} where id = #{id}")
    public Integer editStatus(String status, String id);
}
