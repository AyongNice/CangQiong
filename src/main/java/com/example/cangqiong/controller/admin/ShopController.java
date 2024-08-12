package com.example.cangqiong.controller.admin;


import com.example.cangqiong.service.admin.ShopService;
import com.example.cangqiong.utlis.JwtUtil;
import com.example.cangqiong.utlis.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/shop")
@Slf4j
public class ShopController {


    @Autowired
    private ShopService shopService;


    @Autowired
    JwtUtil jwtUtil;
    //获取营业状态

    @GetMapping("/status")
    public Result<Integer> getShopStatus(@RequestHeader("Token") String token) {

        return Result.success(shopService.getShopStatus(jwtUtil.getID(token)));

    }

    @PutMapping("/{status}")
    public Result<String> setShopStatus(@PathVariable String status, @RequestHeader("Token") String token) {

        shopService.setShopStatus(token, status);

        return Result.success("修改成功");

    }

}
