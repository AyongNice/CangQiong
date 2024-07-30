package com.example.cangqiong.controller.user;


import com.example.cangqiong.dto.UserLoginDto;
import com.example.cangqiong.service.user.UserLoginService;
import com.example.cangqiong.utlis.Result;
import com.example.cangqiong.vo.StoreInfo;
import com.example.cangqiong.vo.UserLoginVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/user/user")
public class UserController {


    @Autowired
    private UserLoginService loginService;

    /**
     * login
     */
    @PostMapping("/login")
    public Result<UserLoginVo> login(@RequestBody UserLoginDto userLoginDto) {

        return Result.success(loginService.login(userLoginDto));
    }

    @GetMapping("/store/list")

    public Result<List<StoreInfo>> getStoreList() {

        return Result.success(loginService.getStoreList());
    }


}
