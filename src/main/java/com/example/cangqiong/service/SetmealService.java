package com.example.cangqiong.service;


import com.example.cangqiong.dto.SetmealDto;
import com.example.cangqiong.mapper.SetmealMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SetmealService {


    @Autowired
    private SetmealMapper setmealMapper;

    public Integer addSetmeal(SetmealDto setmealDto)  {
        setmealDto.setCreateTime(LocalDateTime.now());
        setmealDto.setUpdateTime(LocalDateTime.now());
        return setmealMapper.addSetmeal(setmealDto);
    }

}
