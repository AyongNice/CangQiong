package com.example.cangqiong.aop;

import com.example.cangqiong.constant.UserConst;
import com.example.cangqiong.dto.SetmealDto;
import com.example.cangqiong.utlis.BaseImageEntity;
import com.example.cangqiong.utlis.FilePathToBase64;
import com.example.cangqiong.vo.PageVo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;


@Component
@Aspect
public class ImageConversionAspect {

    @Autowired
    private FilePathToBase64 filePathToBase64;

    @Around("@annotation(com.example.cangqiong.aop.ConvertImageToBase64)")
    public Object convertImagesToBase64(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed(); // 执行原方法

        System.out.println("result: " + result);
        if (result instanceof List) {
            List<?> resultList = (List<?>) result;
            resultList.forEach(item -> {
                if (item instanceof SetmealDto) {
                    SetmealDto entity = (SetmealDto) item;
                    String base64String = filePathToBase64.convertFilePathToBase64(entity.getImage());
                    entity.setImage(UserConst.mimeTypePrefix + base64String); // 使用工具类方法转换并设置图片
                }
            });
        }


        return result;
    }
}
