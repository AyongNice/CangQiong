package com.example.cangqiong.controller.user;


import com.example.cangqiong.dto.CartDto;
import com.example.cangqiong.service.ShoppingCartsService;
import com.example.cangqiong.utlis.Result;
import com.example.cangqiong.vo.CartVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public Result<Integer> addShoppingCart(@RequestBody CartDto cartDto, @RequestHeader("authentication") String authentication) {
        log.info("添加购物车", cartDto);
        return Result.success(shoppingCartsService.add(cartDto, authentication));
    }


    /**
     * 获取购物车列表
     *
     * @param authentication
     * @return
     */
    @GetMapping("/list")
    public Result<List<CartVo>> getShoppingCartList(@Param("storeId") String storeId, @RequestHeader("authentication") String authentication) {
        log.info("获取购物车列表");
        return Result.success(shoppingCartsService.getShoppingCartList(storeId, authentication));
    }


}
