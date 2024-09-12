package com.example.cangqiong.controller.user;


import com.example.cangqiong.entity.dto.CartDto;
import com.example.cangqiong.service.user.ShoppingCartsService;
import com.example.cangqiong.utlis.Result;
import com.example.cangqiong.entity.vo.CartVo;
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
        try {
            shoppingCartsService.add(cartDto, authentication);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }

    }


    @DeleteMapping("/clean")
    public Result<String> cleanShoppingCart(@RequestBody Map<String, String> map, @RequestHeader("authentication") String authentication) {
        try {
            shoppingCartsService.cleanShoppingCart(map.get("storeId"), authentication);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }


    }

    @PostMapping("/sub")
    public Result<Integer> subShoppingCart(@RequestBody CartDto cartDto, @RequestHeader("authentication") String authentication) {
        try {
            shoppingCartsService.deleteCart(cartDto, authentication);

            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }


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
