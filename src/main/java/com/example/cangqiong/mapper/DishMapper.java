package com.example.cangqiong.mapper;


import com.example.cangqiong.dto.DishDto;
import com.example.cangqiong.dto.Flavor;
import lombok.Data;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface DishMapper {

    public Integer addDish(DishDto dishDto);


    //    @Select("select * from dish where category_id = #{categoryId} and name like concat('%',#{name},'%') and status = #{status}")
    public List<DishDto> page(String categoryId, String name, String status, String storeId);


    @Select("select * from dish where category_id = #{categoryId} and store_id = #{storeId} ")
    public List<DishDto> list(String categoryId, String storeId);


    //    @Select("select * from dish where id = #{id}")
    public DishDto getDishById(String id, String storeId);

    public void addFlavors(List<Flavor> flavors);


    @Select("select id, dish_id as dishId , name ,value from dish_flavor where dish_id = #{dishId}")
    public List<Flavor> getFlavors(String dishId);


    @Delete("delete from dish_flavor where dish_id = #{id}")
    public Integer deleteFlavors(String id);

    @Update("update dish set name = #{name}, category_id = #{categoryId}, description = #{description}, image = #{image}, price = #{price}, status = #{status} where id = #{id} and store_id = #{updateUser}")
    public Integer editDish(DishDto dishDto);


    @Update("update dish set status = #{status} where id = #{id} and store_id = #{storeId}")
    public Integer editStatus(String status, String id, String storeId);


    public Integer deleteDish(String[] ids, String storeId);
}
