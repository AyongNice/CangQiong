package com.example.cangqiong.mapper;


import com.example.cangqiong.dto.LongDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface Login {

    @Select("select password,name,id from employee where username=#{username}")
    public LongDto getPassword(LongDto loginDto);
}
