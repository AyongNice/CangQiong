package com.example.cangqiong.service;


import com.example.cangqiong.constant.UserConst;
import com.example.cangqiong.dto.CategoryDto;
import com.example.cangqiong.mapper.CategoryMapper;
import com.example.cangqiong.utlis.FilePathToBase64;
import com.example.cangqiong.utlis.Result;
import com.example.cangqiong.vo.PageVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryService {


    @Autowired
    CategoryMapper categoryMapper;

    /**
     * 新增
     *
     * @param categoryDto
     * @return
     */
    public Integer addCategory(CategoryDto categoryDto) {

        categoryDto.setStatus(1);
        categoryDto.setCreateTime(LocalDateTime.now());
        categoryDto.setUpdateTime(LocalDateTime.now());

        return categoryMapper.addCategory(categoryDto);
    }

    /**
     * 查询分类
     *
     * @param type
     * @return
     */
    public List<CategoryDto> list(Integer type) {

        return categoryMapper.list(type);
    }

    public PageVo<CategoryDto> page(Integer pageNum, Integer pageSize, String name, String type) {

        PageHelper.startPage(pageNum, pageSize);
        List<CategoryDto> list = categoryMapper.page(name, type);

        // 使用 PageInfo 来获取分页信息
        PageInfo<CategoryDto> pageInfo = new PageInfo<>(list);

        return new PageVo<>(pageInfo.getTotal(), pageInfo.getList());
    }

    public Integer editStatus(String status, Integer id) {
       return  categoryMapper.editStatus(status, id);

    }

    public Integer editCategory(CategoryDto categoryDto) {
        return categoryMapper.editCategory(categoryDto);

    }

    public Integer deleteCategory(Integer id) {
        return categoryMapper.deleteCategory(id);
    }
}
