package com.example.cangqiong.controller.admin;


import com.example.cangqiong.constant.Static;
import com.example.cangqiong.utlis.Result;
import jakarta.servlet.annotation.MultipartConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


/**
 * 通用接口
 */
@RestController
@RequestMapping("/admin/common")
@Slf4j
public class CommonController {


    @Autowired
    private Static static1;

    @PostMapping("/upload")
    public Result upLoad(MultipartFile file) throws IOException {

        String filename = file.getOriginalFilename();
        file.transferTo(new File(static1.getPath()  + filename));
        System.out.println(file);

        return Result.success(static1.getPath()  + filename);
    }

}
