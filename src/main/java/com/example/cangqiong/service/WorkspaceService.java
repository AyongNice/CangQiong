package com.example.cangqiong.service;


import com.example.cangqiong.mapper.WorkspaceMapper;
import com.example.cangqiong.vo.SetmealsSum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkspaceService {

    @Autowired
    private WorkspaceMapper workspaceMapper;

    public SetmealsSum overviewSetmeals() {

        return workspaceMapper.overviewSetmeals();
    }

    public SetmealsSum overviewDishes() {
        return workspaceMapper.overviewDishes();
    }
}
