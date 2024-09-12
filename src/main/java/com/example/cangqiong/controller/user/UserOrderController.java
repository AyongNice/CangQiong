package com.example.cangqiong.controller.user;


import com.example.cangqiong.entity.dto.OrderSubmit;
import com.example.cangqiong.entity.dto.PaymentDto;
import com.example.cangqiong.service.user.OrderSubmitService;
import com.example.cangqiong.utlis.Result;
import com.example.cangqiong.entity.vo.OrderSubmitVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;


/**
 * @author cangqiong订单管理
 */
@Slf4j
@RestController
@RequestMapping("/user/order")
public class UserOrderController {


    @Autowired
    private OrderSubmitService orderSubmitService;

    /**
     * 订单提交
     */
    @PostMapping("/submit")
    public Result<OrderSubmitVo> submit(@RequestBody OrderSubmit orderSubmit, @RequestHeader("authentication") String authentication) {

        try {
            return Result.success(orderSubmitService.submit(orderSubmit, authentication));
        } catch (Exception e) {
            log.error("订单提交失败", e);
            return Result.error("订单提交失败");
        }

    }

    /**
     * 支付
     */
    @PutMapping("/payment")
    public Result<Map<String, LocalDateTime>> payment(@RequestBody PaymentDto paymentDto) {


        try {
            return Result.success(orderSubmitService.payment(paymentDto));
        } catch (Exception e) {
            log.error("支付失败", e);
            return Result.error("支付失败");
        }

    }


}
