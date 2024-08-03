package com.example.cangqiong.controller.user;


import com.example.cangqiong.dto.CartDto;
import com.example.cangqiong.service.ShoppingCartsService;
import com.example.cangqiong.utlis.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 购物车控制层
 */

@Slf4j
@RestController
@RequestMapping("/user/shoppingCart")
public class ShoppingCart {


    @Autowired
    private ShoppingCartsService shoppingCartsService;

    @PostMapping("/add")
    public Result<Integer> addShoppingCart(@RequestMapping CartDto cartDto) {
        return shoppingCartsService.add(cartDto);
    }


}
