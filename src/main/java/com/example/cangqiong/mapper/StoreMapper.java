package com.example.cangqiong.mapper;


import com.example.cangqiong.vo.StoreInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StoreMapper {


    @Select("select id,name, boss as bossId from store_admin")
    public List<StoreInfo> getStoreList();
}
