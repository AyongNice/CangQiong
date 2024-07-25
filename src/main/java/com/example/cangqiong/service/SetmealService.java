package com.example.cangqiong.service;


import com.example.cangqiong.dto.SetmealDto;
import com.example.cangqiong.mapper.SetmealMapper;
import com.example.cangqiong.vo.PageVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SetmealService {


    @Autowired
    private SetmealMapper setmealMapper;


    @Transactional
    public Integer addSetmeal(SetmealDto setmealDto) {
        setmealDto.setCreateTime(LocalDateTime.now());
        setmealDto.setUpdateTime(LocalDateTime.now());
        Long setmealId = System.currentTimeMillis();

        setmealDto.setId(Long.valueOf(setmealId.toString()));

        //循环 getSetmealDishes() 拆入  setmealId
        setmealDto.getSetmealDishes().forEach(setmealDishesDto -> {
            setmealDishesDto.setSetmealId(Long.valueOf(setmealId.toString()));
        });


//        try {
        setmealMapper.addSetmealDishes(setmealDto.getSetmealDishes());
        return setmealMapper.addSetmeal(setmealDto);
//        } catch (Exception e) {
//            return 0;
//        }

    }

    public PageVo<SetmealDto> page(Integer page, Integer pageSize, String name, String status, String categoryId) {

        PageHelper.startPage(page, pageSize);
        List<SetmealDto> list = setmealMapper.page(name, status, categoryId);

        PageInfo<SetmealDto> pageInfo = new PageInfo<>(list);

        return new PageVo<>(pageInfo.getTotal(), pageInfo.getList());


    }

    public SetmealDto getSetmeal(Integer id) {
        return setmealMapper.getSetmeal(id);
    }
}
