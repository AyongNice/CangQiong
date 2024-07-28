package com.example.cangqiong.service;


import com.example.cangqiong.mapper.WorkspaceMapper;
import com.example.cangqiong.vo.SetmealsSumVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkspaceService {

    @Autowired
    private WorkspaceMapper workspaceMapper;


    //套餐统计
    public SetmealsSumVo overviewSetmeals() {

        return workspaceMapper.overviewSetmeals();
    }


    //    菜品统计
    public SetmealsSumVo overviewDishes() {
        return workspaceMapper.overviewDishes();
    }
}
