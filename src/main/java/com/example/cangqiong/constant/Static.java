package com.example.cangqiong.constant;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "static")
@Data
public class Static {

    private  String path;
}
