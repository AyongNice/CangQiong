package com.example.cangqiong.utlis;

import java.util.Random;

public class CartIdGenerator {


    /**
     * 生成唯一购物车ID
     *
     * @return
     */
    public static int generateUniqueCartId() {
        Random random = new Random();
        // 生成一个范围在 [10000, 99999] 内的随机数
        return 1000 + random.nextInt(9000);
    }
}
