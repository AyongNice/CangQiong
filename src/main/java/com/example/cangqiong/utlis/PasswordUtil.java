package com.example.cangqiong.utlis;

import org.springframework.util.DigestUtils;

public class PasswordUtil {

    // 加密密码
    public static String hashPassword(String password) {
        return DigestUtils.md5DigestAsHex(password.getBytes());
    }

    // 验证密码
    public static boolean checkPassword(String password, String hashedPassword) {
       return  hashedPassword.equals(hashPassword(password));
    }
}
