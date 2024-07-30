package com.example.cangqiong.mapper;


import com.example.cangqiong.dto.Address;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public interface  AddressMapper {

    public Integer addAddress(Address address);
}
