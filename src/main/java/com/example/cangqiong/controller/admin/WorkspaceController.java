package com.example.cangqiong.controller.admin;


import com.example.cangqiong.service.WorkspaceService;
import com.example.cangqiong.utlis.Result;
import com.example.cangqiong.vo.SetmealsSum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@Controller
@RequestMapping("/admin/workspace")
public class WorkspaceController {


    @Autowired
    private WorkspaceService workspaceService;

    /**
     * 套餐总览
     */
    @GetMapping("/overviewSetmeals")
    public Result<SetmealsSum> overviewSetmeals() {

        SetmealsSum setmealsSum = workspaceService.overviewSetmeals();

        return Result.success(setmealsSum);

    }

    /**
     * 菜品总览
     */

    @GetMapping("/overviewDishes")
    public Result<SetmealsSum> overviewDishes() {

        SetmealsSum setmealsSum = workspaceService.overviewDishes();

        return Result.success(setmealsSum);

    }


}
