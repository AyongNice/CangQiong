package com.example.cangqiong.service.admin;

import com.example.cangqiong.constant.JwtProperties;
import com.example.cangqiong.dto.CategoryDto;
import com.example.cangqiong.mapper.CategoryMapper;
import com.example.cangqiong.utlis.JwtUtil;
import com.example.cangqiong.vo.PageVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;




@Service
public class CategoryService {


    @Autowired
    CategoryMapper categoryMapper;

    @Qualifier("jwtProperties")
    @Autowired
    JwtProperties jwtProperties;


    @Autowired
    JwtUtil jwtUtil;


    /**
     * 新增
     *
     * @param categoryDto
     * @return
     */
    public Integer addCategory(CategoryDto categoryDto, String token) {

        categoryDto.setCreateUser(Integer.valueOf(jwtUtil.getID(token)));

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
    public List<CategoryDto> list(Integer type, String storeId) {

        return categoryMapper.list(type, storeId);
    }

    public PageVo<CategoryDto> page(Integer pageNum, Integer pageSize, String name, String type, String storeId) {

        PageHelper.startPage(pageNum, pageSize);
        List<CategoryDto> list = categoryMapper.page(name, type, storeId);

        // 使用 PageInfo 来获取分页信息
        PageInfo<CategoryDto> pageInfo = new PageInfo<>(list);

        return new PageVo<>(pageInfo.getTotal(), pageInfo.getList());
    }


    /**
     * 编辑分状态
     * @param status
     * @param id
     * @param token
     * @return
     */
    public Integer editStatus(String status, Integer id, String token) {
        return categoryMapper.editStatus(status, id, jwtUtil.getID(token));

    }


    /**
     * 编辑分类
     * @param categoryDto
     * @param token
     * @return
     */
    public Integer editCategory(CategoryDto categoryDto, String token) {

        categoryDto.setUpdateUser(jwtUtil.getID(token));
        return categoryMapper.editCategory(categoryDto);

    }

    public Integer deleteCategory(Integer id, String token) {
        return categoryMapper.deleteCategory(id, jwtUtil.getID(token));
    }
}
