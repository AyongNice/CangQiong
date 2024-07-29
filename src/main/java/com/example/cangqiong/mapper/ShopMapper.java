package com.example.cangqiong.mapper;


import com.example.cangqiong.vo.StoreVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ShopMapper {


//    @Select("select status from shop")
    public Integer getShopStatus();


    @Select("select id,name,boss_id as bossId from  store")
    public List<StoreVo> storeList();
}
