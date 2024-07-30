package com.example.cangqiong.utlis;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;


@Component
public class FilePathToBase64 {

    public String convertFilePathToBase64(String filePath) {
        try {
            Path path = Paths.get(filePath);
            // 使用Base64编码器将字节数组转换为Base64字符串
            return Base64.getEncoder().encodeToString(Files.readAllBytes(path));
        } catch (InvalidPathException e) {
            // 特定于路径无效的异常处理
            System.err.println("Invalid file path: " + filePath);
            return "INVALID_PATH";
        } catch (IOException e) {
            // 其他IO异常处理
            System.err.println("Error reading file: " + filePath);
            return "ERROR_READING_FILE";
        }
    }


}
