package com.example.cangqiong.excepttion;


import com.example.cangqiong.utlis.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Excepttion {


    @ExceptionHandler(Exception.class)
    public Result reEstpon(Exception e){

        e.printStackTrace();
        return Result.error(e.getMessage());
    }

}
