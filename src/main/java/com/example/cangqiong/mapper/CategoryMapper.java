package com.example.cangqiong.mapper;


import com.example.cangqiong.dto.CategoryDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


/**
 * 菜品分类
 * 从token获取操作人id 就是店铺id
 */
@Mapper
public interface CategoryMapper {

    public Integer addCategory(CategoryDto categoryDto);


    /**
     * 获取分类
     *
     * @param type    1:菜品分类 2:套餐分类
     * @param storeId 店铺id === 员工id
     * @return
     */

    public List<CategoryDto> list(Integer type, String storeId);

    public List<CategoryDto> page(String name, String type, String storeId);


    @Update("update category set status = #{status} where id = #{id} and store_id = #{storeId}")
    public Integer editStatus(String status, Integer id, String storeId);


    /**
     * 修改分类 id storeId
     *
     * @param categoryDto
     * @return
     */
    @Update("update category set name = #{name}, type =  #{type}, sort = #{sort} where id = #{id} and store_id = #{updateUser}")
    public Integer editCategory(CategoryDto categoryDto);


    @Delete("delete from category where id = #{id} and store_id =  #{storeId}")
    Integer deleteCategory(Integer id, String storeId);
}
