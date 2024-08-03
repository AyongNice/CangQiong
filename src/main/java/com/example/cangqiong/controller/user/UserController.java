package com.example.cangqiong.controller.user;


import com.example.cangqiong.dto.UserLoginDto;
import com.example.cangqiong.service.user.UserLoginService;
import com.example.cangqiong.utlis.Result;
import com.example.cangqiong.vo.StoreInfo;
import com.example.cangqiong.vo.UserLoginVo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;


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
