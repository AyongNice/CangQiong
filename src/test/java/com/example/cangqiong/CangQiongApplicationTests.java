package com.example.cangqiong;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;


class CangQiongApplicationTests {

    @Test
    void contextLoads() {
        String password = "123456";

        System.out.println(DigestUtils.md5DigestAsHex(password.getBytes()));
    }

}
