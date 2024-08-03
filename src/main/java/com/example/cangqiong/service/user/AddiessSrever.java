package com.example.cangqiong.service.user;


import com.example.cangqiong.dto.Address;
import com.example.cangqiong.mapper.AddressMapper;
import com.example.cangqiong.utlis.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AddiessSrever {

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    JwtUtil jwtUtil;


    public Integer addAddress(Address address, String authentication) {

        //获取 微信小程序用户的openid 作用为用户id
        address.setUserId(jwtUtil.getOpenId(authentication));

        return addressMapper.addAddress(address);
    }

    /**
     * 获取所有地址
     *
     * @param authentication 微信小程序用户的token
     * @return
     */
    public List<Address> getAddressList(String authentication) {
        log.info("获取所有地址" + jwtUtil.getOpenId(authentication));

        return addressMapper.getAddressList(jwtUtil.getOpenId(authentication));

    }

    public Integer setAddressBook(Address address) {

        return addressMapper.setAddressBook(address);
    }

    public Integer setDefaultAddress(String id, String authentication) {
        String userId = jwtUtil.getOpenId(authentication);
        addressMapper.setFeiDefaultAddress(id, userId);
        return addressMapper.setDefaultAddress(id, userId);
    }

    public Address getAddressCurrent(String id) {
        return addressMapper.getAddressCurrent(id);
    }
}
