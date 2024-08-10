package com.example.cangqiong.controller.admin;


import com.example.cangqiong.service.OrderService;
import com.example.cangqiong.service.WorkspaceService;
import com.example.cangqiong.utlis.Result;
import com.example.cangqiong.vo.BusinessDataVo;
import com.example.cangqiong.vo.SetmealsSumVo;
import com.example.cangqiong.vo.StatisticsVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/admin/workspace")
public class WorkspaceController {
    @Autowired
    private WorkspaceService workspaceService;
    @Autowired
    private OrderService orderService;

    /**
     * 套餐总览
     */
    @GetMapping("/overviewSetmeals")
    public Result<SetmealsSumVo> overviewSetmeals() {

        SetmealsSumVo setmealsSum = workspaceService.overviewSetmeals();

        return Result.success(setmealsSum);

    }

    /**
     * 菜品总览
     */

    @GetMapping("/overviewDishes")
    public Result<SetmealsSumVo> overviewDishes() {

        SetmealsSumVo setmealsSum = workspaceService.overviewDishes();

        return Result.success(setmealsSum);

    }

    /**
     * 查询订单管理数据
     * @param token
     * @return
     */
    @GetMapping("/overviewOrders")
    public Result<StatisticsVo> getOrderOverviewOrders(@RequestHeader("Token") String token) {
        return Result.success(orderService.getOrderStatistics(token));
    }


    /**
     * 查询今日运营数据
     * businessData
     */

    @GetMapping("/businessData")
    public Result<BusinessDataVo> getBusinessData(@RequestHeader("Token") String token) {
        return Result.success(orderService.getBusinessData(token));
    }

}
