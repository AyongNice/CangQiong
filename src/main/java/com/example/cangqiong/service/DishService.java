package com.example.cangqiong.service;


import com.example.cangqiong.constant.UserConst;
import com.example.cangqiong.dto.DishDto;
import com.example.cangqiong.dto.Flavor;
import com.example.cangqiong.mapper.DishMapper;
import com.example.cangqiong.utlis.FilePathToBase64;
import com.example.cangqiong.vo.PageVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DishService {

    @Autowired
    DishMapper dishMapper;

    @Autowired
    private FilePathToBase64 filePathToBase64;

    /**
     * 增加菜品
     */
    public Integer addDish(DishDto dishDto) {

        String id = String.valueOf(System.currentTimeMillis());
        dishDto.setId(id);

        dishDto.getFlavors().forEach(flavor -> {
            flavor.setDishId(id);
        });
        dishMapper.addFlavors(dishDto.getFlavors());

        dishDto.setCreateTime(LocalDateTime.now());
        dishDto.setUpdateTime(LocalDateTime.now());
        return dishMapper.addDish(dishDto);
    }

    public PageVo<DishDto> page(Integer pageNum, Integer pageSize, String categoryId, String name, String status) {

        PageHelper.startPage(pageNum, pageSize);
        List<DishDto> list = dishMapper.page(categoryId, name, status);

        list.forEach(dishDto -> {
            dishDto.setImage(UserConst.mimeTypePrefix + filePathToBase64.convertFilePathToBase64(dishDto.getImage()));
        });


        PageInfo<DishDto> pageInfo = new PageInfo<>(list);


        return new PageVo<>(pageInfo.getTotal(), pageInfo.getList());
    }

    /*
     * 查询菜品
     */

    public List<DishDto> list(String categoryId) {

        return dishMapper.list(categoryId);
    }

    /**
     * 根据id查询菜品
     *
     * @param id
     * @return
     */

    public DishDto getDishById(String id) {


        List<Flavor> flavors = dishMapper.getFlavors(id);
        DishDto dishDto = dishMapper.getDishById(id);

        // 图片转base64
        dishDto.setImage(UserConst.mimeTypePrefix + filePathToBase64.convertFilePathToBase64(dishDto.getImage()));

        // 设置口味
        dishDto.setFlavors(flavors);

        return dishDto;
    }


    /*
     * 修改菜品
     */
    public Integer editDish(DishDto dishDto) {

        //删除口味关联
        dishMapper.deleteFlavors(dishDto.getId());

        if (dishDto.getFlavors().size() > 0) {
            //添加口味关联 dish_id
            dishDto.getFlavors().forEach(flavor -> {
                flavor.setDishId(dishDto.getId());
            });
            //重新添加口味关联数据
            dishMapper.addFlavors(dishDto.getFlavors());
        }

        dishDto.setUpdateTime(LocalDateTime.now());
        return dishMapper.editDish(dishDto);


    }

    /**
     * 更菜品状态
     */

    public Integer editStatus(String status, String id) {

        return dishMapper.editStatus(status, id);
    }


    //批量删除菜品
    public Integer deleteDish(String ids) {
        String[] idArray = ids.split(",");
        return dishMapper.deleteDish(idArray);
    }
}

