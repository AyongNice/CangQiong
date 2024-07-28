package com.example.cangqiong.service;


import com.example.cangqiong.aop.ConvertImageToBase64;
import com.example.cangqiong.constant.UserConst;
import com.example.cangqiong.dto.DishDto;
import com.example.cangqiong.dto.SetmealDishesDto;
import com.example.cangqiong.dto.SetmealDto;
import com.example.cangqiong.mapper.SetmealMapper;
import com.example.cangqiong.utlis.FilePathToBase64;
import com.example.cangqiong.utlis.Result;
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


    @Autowired
    private FilePathToBase64 filePathToBase64;


    @Transactional
    public Integer addSetmeal(SetmealDto setmealDto) {
        setmealDto.setCreateTime(LocalDateTime.now());
        setmealDto.setUpdateTime(LocalDateTime.now());

        //生成套餐id
        long setmealId = System.currentTimeMillis();

        setmealDto.setId(Long.toString(setmealId));

        //循环 getSetmealDishes() 拆入  setmealId
        setmealDto.getSetmealDishes().forEach(setmealDishesDto -> {
            setmealDishesDto.setSetmealId(Long.toString(setmealId));
        });


        setmealMapper.addSetmealDishes(setmealDto.getSetmealDishes());

        return setmealMapper.addSetmeal(setmealDto);


    }


    @ConvertImageToBase64
    public List<SetmealDto> getList(String name, String status, String categoryId) {
        return setmealMapper.page(name, status, categoryId);
    }


    @ConvertImageToBase64
    public PageVo<SetmealDto> page(Integer page, Integer pageSize, String name, String status, String categoryId) {

        PageHelper.startPage(page, pageSize);
        List<SetmealDto> list = getList(name, status, categoryId);
        list.forEach(setmealDto -> {
            setmealDto.setImage(UserConst.mimeTypePrefix + filePathToBase64.convertFilePathToBase64(setmealDto.getImage()));
        });
        //分页数据
        PageInfo<SetmealDto> pageInfo = new PageInfo<>(list);
        return new PageVo<>(pageInfo.getTotal(), pageInfo.getList());

    }


    public SetmealDto getSetmeal(String id) {

        List<SetmealDishesDto> list = setmealMapper.getSetmealDishes(id);

        SetmealDto setmealDto = setmealMapper.getSetmeal(id);
        setmealDto.setSetmealDishes(list);
        setmealDto.setImage(UserConst.mimeTypePrefix + filePathToBase64.convertFilePathToBase64(setmealDto.getImage()));

        return setmealDto;
    }


    @Transactional
    public Integer editSetmeal(SetmealDto setmealDto) {
        setmealDto.setUpdateTime(LocalDateTime.now());

        //删除之前原有的套餐 菜品关联
        setmealMapper.deleteSetmealDishes(setmealDto.getId());


        if (setmealDto.getSetmealDishes().size() > 0) {
            //添加菜品关联套餐setmealId
            setmealDto.getSetmealDishes().forEach(setmealDishesDto -> {
                setmealDishesDto.setSetmealId(setmealDto.getId());
            });

            //新增信的菜品关联
            setmealMapper.addSetmealDishes(setmealDto.getSetmealDishes());

        }

        return setmealMapper.editSetmeal(setmealDto);
    }


    @Transactional
    public Integer deleteSetmeal(String[] ids) {
        setmealMapper.deleteSetmealDishesList(ids);
        return setmealMapper.deleteSetmeal(ids);
    }


    //启售 停售
    public Integer editStatus(String status, String id) {
        return setmealMapper.editStatus(status, id);
    }
}
