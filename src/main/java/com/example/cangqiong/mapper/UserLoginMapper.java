package com.example.cangqiong.mapper;


import com.example.cangqiong.entity.dto.UserLoginDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserLoginMapper {


    /**
     * 新增用户
     *
     * @param userLoginDto
     * @return
     */
    public Integer addUser(UserLoginDto userLoginDto);


    /**
     * 根据openId查询用户
     */

    @Select("select * from user where openid = #{openId}")
    public UserLoginDto getUser(String openId);

}
