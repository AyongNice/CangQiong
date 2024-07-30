package com.example.cangqiong.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ShopMapper {


//    @Select("select status from shop")
    public Integer getShopStatus();
}
