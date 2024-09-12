package com.example.cangqiong.mapper;


import com.example.cangqiong.entity.dto.EditPasswordDto;
import com.example.cangqiong.entity.dto.LongDto;
import com.example.cangqiong.entity.dto.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface Admin {

    @Select("select password,name,id from employee where username=#{username}")
    public LongDto getPassword(LongDto loginDto);

//
//    @Insert("insert into employee (name,username,password,phone,sex,id_number,status,create_time,update_time,create_user,update_user) values (#{name},#{username},#{sex},#{password},#{status},#{createTime},#{updateTime},#{createUser},#{updateUser});")
//    public void addEmployee(User user);

    @Insert("INSERT INTO employee (name, username, password, phone, sex, id_number, status, create_time, update_time, create_user, update_user) " +
            "VALUES (#{name}, #{username}, #{password}, #{phone}, #{sex}, #{idNumber}, #{status}, " +
            "#{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    Integer addEmployee(User user);


    public List<User> page(Integer start, Integer pageSize, String name);

    //查询总数
    public Integer count(String name);


    //根据id查处询员工密码
    @Select("select password from employee where id=#{empId}")
    public String getPasswordById(String loginDto);

    //修改密码
    @Update("update employee set password=#{newPassword} where id=#{empId}")
    public Integer editPassword(EditPasswordDto editPasswordDto);


    //修改员工信息
    public Integer editEmployee(User user);

    //查询员工
    @Select("select name, username, phone, sex, id_number from employee where id=#{id}")
    public User getEmployeeById(String id);
}
