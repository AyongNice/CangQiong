package com.example.cangqiong.service;


import com.example.cangqiong.dto.DishDto;
import com.example.cangqiong.mapper.DishMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DishService {

    @Autowired
    DishMapper dishMapper;


    /**
     * 增加菜品
     */
    public Integer addDish(DishDto dishDto) {
      return  dishMapper.addDish(dishDto);
    }

}
