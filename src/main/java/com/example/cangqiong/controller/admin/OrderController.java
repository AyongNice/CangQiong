package com.example.cangqiong.controller.admin;


import com.example.cangqiong.dto.OrderAdminDto;
import com.example.cangqiong.dto.OrderSubmit;
import com.example.cangqiong.service.OrderService;
import com.example.cangqiong.utlis.Result;
import com.example.cangqiong.vo.PageVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 查询当前店铺订单
     */
    @GetMapping("/conditionSearch")
    public Result<PageVo<OrderSubmit>> getOrderPage(@ModelAttribute OrderAdminDto orderAdminDto, @RequestHeader("Token") String token) {

        return Result.success(orderService.getOrderPage(orderAdminDto, token));
    }
}
