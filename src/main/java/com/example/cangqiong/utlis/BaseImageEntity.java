package com.example.cangqiong.utlis;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public abstract class BaseImageEntity {

    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String imagePath) {
        try {
            Path path = Paths.get(imagePath);
            byte[] fileBytes = Files.readAllBytes(path);
            String base64String = Base64.getEncoder().encodeToString(fileBytes);
            this.image = "data:image/png;base64," + base64String; // 假设图片是PNG格式
        } catch (IOException e) {
            e.printStackTrace();
            // 可以选择抛出异常或设置默认值
        }
    }
}
