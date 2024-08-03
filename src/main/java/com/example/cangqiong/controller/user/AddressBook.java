package com.example.cangqiong.controller.user;


import com.example.cangqiong.dto.Address;
import com.example.cangqiong.service.user.AddiessSrever;
import com.example.cangqiong.utlis.JwtUtil;
import com.example.cangqiong.utlis.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user/addressBook")
public class AddressBook {


    @Autowired
    private AddiessSrever addiessSrever;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 新增
     *
     * @param address
     * @return
     */
    @PostMapping
    public Result<Integer> addAddressBook(@RequestBody Address address, @RequestHeader("authentication") String authentication) {

        return Result.success(addiessSrever.addAddress(address, authentication));

    }


    @GetMapping("/list")
    public Result<List<Address>> getAddressList(@RequestHeader("authentication") String authentication) {



        return Result.success(addiessSrever.getAddressList(authentication));
    }


    @PutMapping
    public Result<Integer> setAddressBook(@RequestBody Address address) {

        return Result.success(addiessSrever.setAddressBook(address));
    }

    @GetMapping("/{id}")
    public Result<Address> getAddressCurrent(@PathVariable String id) {

        return Result.success(addiessSrever.getAddressCurrent(id));
    }


    @PutMapping("/default")
    public Result<Integer> setDefaultAddress(@RequestBody Map<String, String> map, @RequestHeader("authentication") String authentication) {

        return Result.success(addiessSrever.setDefaultAddress(map.get("id"), authentication));
    }


}
