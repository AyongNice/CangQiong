package com.example.cangqiong.service.user;


import com.example.cangqiong.dto.Address;
import com.example.cangqiong.mapper.AddressMapper;
import com.example.cangqiong.utlis.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddiessSrever {

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    JwtUtil jwtUtil;


    public Integer addAddress(Address address,String authentication) {

        //获取 微信小程序用户的openid 作用为用户id
        address.setUserId(jwtUtil.getOpenId(authentication));

        return addressMapper.addAddress(address);
    }
}
