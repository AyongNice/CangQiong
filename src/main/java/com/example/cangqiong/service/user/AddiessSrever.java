package com.example.cangqiong.service.user;


import com.example.cangqiong.dto.Address;
import com.example.cangqiong.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddiessSrever {

    @Autowired
    private AddressMapper addressMapper;

    public Integer addAddress(Address address) {
        return addressMapper.addAddress(address);
    }
}
