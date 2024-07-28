package com.example.cangqiong.utlis;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;


@Component
public class FilePathToBase64 {

    public String convertFilePathToBase64(String filePath) {

        try {
            Path path = Paths.get(filePath);
            // 读取文件的所有字节
            byte[] fileBytes = Files.readAllBytes(path);

            // 使用Base64编码器将字节数组转换为Base64字符串
            String base64String = Base64.getEncoder().encodeToString(fileBytes);

            return base64String;
        } catch (IOException e) {
            // 处理异常，例如记录错误或抛出运行时异常
            e.printStackTrace();
            return null;
        }
    }


}
