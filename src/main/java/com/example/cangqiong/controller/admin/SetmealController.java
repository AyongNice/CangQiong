package com.example.cangqiong.controller.admin;

import com.example.cangqiong.constant.JwtProperties;
import com.example.cangqiong.dto.SetmealDto;
import com.example.cangqiong.service.SetmealService;
import com.example.cangqiong.utlis.JwtUtil;
import com.example.cangqiong.utlis.Result;
import com.example.cangqiong.vo.PageVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin/setmeal")
@Slf4j
public class SetmealController {

    @Autowired
    private SetmealService setmealService;


    @Qualifier("jwtProperties")
    @Autowired
    JwtProperties jwtProperties;

    @Autowired
    JwtUtil jwtUtil;

    /**
     * 套餐新增
     *
     * @param setmealDto
     * @param token
     * @return
     */

    @PostMapping
    public Result<String> addSetmeal(@RequestBody SetmealDto setmealDto, @RequestHeader("Token") String token) {


        return setmealService.addSetmeal(setmealDto, token) > 0 ? Result.success() : Result.error("修改失败");

    }

    /**
     * 修改套餐
     *
     * @param id
     * @return
     */
    @PutMapping
    public Result<String> editSetmeal(@RequestBody SetmealDto setmealDto, @RequestHeader("Token") String token) {


        return setmealService.editSetmeal(setmealDto, token) == 1 ? Result.success() : Result.error("修改失败");
    }


    /**
     * 套餐根据id查询
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<SetmealDto> getSetmeal(@PathVariable String id, @RequestHeader("Token") String token) {


        return Result.success(setmealService.getSetmeal(id, jwtUtil.getID(token)));
    }


    //分页
    @GetMapping("/page")
    public Result<PageVo<SetmealDto>> page(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize, @Param("mame") String name, @Param("status") String status, @Param("categoryId") String categoryId, @RequestHeader("Token") String token) {


        return Result.success(setmealService.page(page, pageSize, name, status, categoryId, jwtUtil.getID(token)));
    }

    @DeleteMapping
    public Result<String> deleteSetmeal(@RequestParam String ids, @RequestHeader("Token") String token) {
        //将ids字符串转换为字符串集合List

        return setmealService.deleteSetmeal(ids.split(","), token) > 0 ? Result.success() : Result.error("删除失败");
    }

    @PostMapping("/status/{status}")
    public Result<String> editStatus(@PathVariable String status, @Param("id") String id, @RequestHeader("Token") String token) {


        return setmealService.editStatus(status, id, token) > 0 ? Result.success() : Result.error("修改失败");
    }


}
