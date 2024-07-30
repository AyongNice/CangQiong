package com.example.cangqiong.controller.user;


import com.example.cangqiong.dto.Address;
import com.example.cangqiong.service.user.AddiessSrever;
import com.example.cangqiong.utlis.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user/addressBook")
public class AddressBook {


    @Autowired
    private AddiessSrever addiessSrever;

    //获取地址

    @GetMapping("/list")
    public Result<AddressBook> getAddressBook() {
        return Result.success();
    }

    /**
     * 新增
     *
     * @param address
     * @return
     */
    @PostMapping
    public Result<Integer> addAddressBook(Address address) {

        return Result.success(addiessSrever.addAddress(address));

    }


}
