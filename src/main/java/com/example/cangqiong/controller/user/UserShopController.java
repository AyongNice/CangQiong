package com.example.cangqiong.controller.user;


import com.example.cangqiong.service.ShopService;
import com.example.cangqiong.utlis.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user/shop")
public class UserShopController {


    @Autowired
    private ShopService shopService;

    @GetMapping("/status")
    public Result<Integer> getShopStatus() {
        return Result.success(shopService.getShopStatus("1"));

    }


}
