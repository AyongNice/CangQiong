package com.example.cangqiong.controller.admin;


import com.example.cangqiong.constant.TakeOrders;
import com.example.cangqiong.dto.OrderAdminDto;
import com.example.cangqiong.dto.OrderSubmit;
import com.example.cangqiong.dto.TakeOrdersDto;
import com.example.cangqiong.service.admin.OrderService;
import com.example.cangqiong.utlis.Result;
import com.example.cangqiong.vo.PageVo;
import com.example.cangqiong.vo.StatisticsVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/details/{orderId}")
    public Result<OrderSubmit> getOrderDetails(@PathVariable String orderId, @RequestHeader("Token") String token) {
        return Result.success(orderService.getOrderDetails(orderId, token));
    }

    /**
     * 接单
     */
    @PutMapping("/confirm")
    public Result<String> confirmOrder(@RequestBody TakeOrdersDto takeOrdersDto, @RequestHeader("Token") String token){
        takeOrdersDto.setStatus(TakeOrders.ACCEPTED.getCode());
        orderService.manipulateOrders(takeOrdersDto,token);
        return Result.success("接单成功");
    }
    /**
     * 拒单
     */
    @PutMapping("/rejection")
    public Result<String> rejectionOrder(@RequestBody TakeOrdersDto takeOrdersDto, @RequestHeader("Token") String token) {
        takeOrdersDto.setStatus(TakeOrders.REJECT.getCode());
        orderService.manipulateOrders(takeOrdersDto,token);
        return Result.success("拒单成功");
    }
    /**
     *派送
     */
    @PutMapping("/delivery/{id}")
    public Result<String> deliveryOrder(@PathVariable String id, @RequestHeader("Token") String token) {
        TakeOrdersDto takeOrdersDto = new TakeOrdersDto();
        takeOrdersDto.setId(id);
        takeOrdersDto.setStatus(TakeOrders.DELIVERING.getCode());
        orderService.manipulateOrders(takeOrdersDto,token);
        return Result.success("派送成功");
    }
    /**
     * 完成订单
     * @param id
     * @param token
     * @return
     */
    @PutMapping("/complete/{id}")
    public Result<String> completeOrder(@PathVariable String id, @RequestHeader("Token") String token) {
        TakeOrdersDto takeOrdersDto = new TakeOrdersDto();
        takeOrdersDto.setId(id);
        takeOrdersDto.setStatus(TakeOrders.COMPLETED.getCode());
        orderService.manipulateOrders(takeOrdersDto,token);
        return Result.success("派送成功");
    }


    /**
     * 订单统计
     * @param token
     * @return
     */
    @GetMapping("/statistics")
    public Result<StatisticsVo> getOrderStatistics(@RequestHeader("Token") String token) {
        return Result.success(orderService.getOrderStatistics(token));
    }




}
