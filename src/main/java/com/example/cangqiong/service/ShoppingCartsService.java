package com.example.cangqiong.service;


import com.example.cangqiong.dto.CartDto;

import com.example.cangqiong.dto.DishDto;
import com.example.cangqiong.utlis.CartIdGenerator;
import com.example.cangqiong.utlis.JwtUtil;
import com.example.cangqiong.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ShoppingCartsService {

    @Autowired
    private StringRedisTemplate strRedisT;


    @Autowired
    private DishService dishService;


    @Autowired
    private JwtUtil jwtUtil;

    public Integer add(CartDto cartDto, String authentication) {

        String dishId = cartDto.getDishId();

        String name = null;
        BigDecimal amount1 = null;
        if (dishId != null) {
            DishDto dishDto = dishService.getDishById(dishId, cartDto.getStoreId());
            //查询菜品价格
            amount1 = new BigDecimal(dishDto.getPrice());
            name = dishDto.getName();
        }


        cartDto.setUserId(jwtUtil.getOpenId(authentication));

        // 获取 HashOperations 对象
        HashOperations<String, String, String> hashOps = strRedisT.opsForHash();
        // 获取 SetOperations 对象
        SetOperations<String, String> setOps = strRedisT.opsForSet();
        // 存储数据
        String key = "cart:" + cartDto.getStoreId() + cartDto.getUserId() + CartIdGenerator.generateUniqueCartId(); // 假设每个 cartId 作为哈希的键

        //套餐id
        String setmealId = cartDto.getSetmealId();
        String field1 = "setmealId";
        String value1 = setmealId == null ? " " : setmealId;

        //口味id
        String dishFlavorId = cartDto.getDishFlavorId();
        String field2 = "dishFlavorId";
        String value2 = dishFlavorId == null ? " " : dishFlavorId;

        //菜品id
        String field3 = "dishId";
        String value3 = dishId == null ? " " : dishId;


        //店铺id
        String field4 = "storeId";
        String value4 = cartDto.getStoreId();
        String field5 = "userId";
        String value5 = cartDto.getUserId();

        String field6 = "amount";
        String value6 = amount1 == null ? " " : amount1.toString();

        String field7 = "name";
        String value7 = name == null ? " " : name;

        hashOps.put(key, field1, value1);
        hashOps.put(key, field2, value2);
        hashOps.put(key, field3, value3);
        hashOps.put(key, field4, value4);
        hashOps.put(key, field5, value5);
        hashOps.put(key, field6, value6);
        hashOps.put(key, field7, value7);


        // 存储 userId 与 cartId 的关系
        setOps.add("userId:" + cartDto.getUserId(), key);

        // 存储 storeId 与 cartId 的关系
        setOps.add("storeId:" + cartDto.getStoreId(), key);
        System.out.println("添加成功");


        return 1;
    }


    public List<CartVo> getShoppingCartList(String storeId, String authentication) {

        String userId = jwtUtil.getOpenId(authentication);
        RedisConnectionFactory factory = strRedisT.getConnectionFactory();

        // 1. 获取所有以 "cart:" 开头的 key
        List<String> cartKeys = new ArrayList<>();
        if (factory != null) {
            try (RedisConnection connection = factory.getConnection()) {
                Cursor<byte[]> cursor = connection.scan(ScanOptions.scanOptions().match("cart:*").count(100).build());
                while (cursor.hasNext()) {
                    byte[] keyBytes = cursor.next();
                    cartKeys.add(strRedisT.getStringSerializer().deserialize(keyBytes));
                }
            }
        }

        // 2. 筛选出包含 userId 和 storeId 的 key
        List<String> filteredKeys = new ArrayList<>();
        for (String key : cartKeys) {
            if (key.contains(storeId) && key.contains(userId)) {
                filteredKeys.add(key);
            }
        }

        // 3. 根据筛选出的 key 获取相应的数据
        List<CartVo> cartItemsList = new ArrayList<>();
        for (String filteredKey : filteredKeys) {
            Map<Object, Object> cartItemMap = strRedisT.opsForHash().entries(filteredKey);
            cartItemsList.add(convertToCartVo(cartItemMap, filteredKey));
        }

        return processCartItems(cartItemsList);
    }

    /**
     * 将从 Redis 中读取的 Map 转换成 CartVo 对象
     *
     * @param cartItemMap 从 Redis 中读取的 Map
     * @return CartVo 对象
     */
    private CartVo convertToCartVo(Map<Object, Object> cartItemMap, String cartId) {
        CartVo cartItem = new CartVo();
        cartItem.setId(cartId);

        Object setmealId = cartItemMap.get("setmealId");
        if (setmealId != null) {
            cartItem.setSetmealId(String.valueOf(setmealId));
        }

        Object name = cartItemMap.get("name");
        if (setmealId != null) {
            cartItem.setName(String.valueOf(name));
        }


        Object dishFlavorId = cartItemMap.get("dishFlavorId");
        if (dishFlavorId != null) {
            cartItem.setDishFlavor(String.valueOf(cartItemMap.get("dishFlavorId")));
        }

        Object dishId = cartItemMap.get("dishId");
        if (dishId != null) {
            cartItem.setDishId(String.valueOf(cartItemMap.get("dishId")));
        }


        // 将字符串形式的金额转换为 Integer 类型
        String amountStr = (String) cartItemMap.get("amount");
        if (amountStr != null && !amountStr.trim().isEmpty()) {
            cartItem.setAmount(Integer.parseInt(amountStr.trim()));
        } else {
            cartItem.setAmount(0); // 设置为默认值 0
        }
        return cartItem;
    }


    /**
     * List 中 dishId 或 setmealId 去重 然后向当前对象的number+1
     *
     * @param cartItems
     * @return
     */
    public List<CartVo> processCartItems(List<CartVo> cartItems) {
        // 使用 Stream API 分组并累加数量
        Map<CartVo, CartVo> groupedCartItems = cartItems.stream()
                .collect(Collectors.toMap(
                        this::createKey, // 自定义 key 生成器
                        cartVo -> cartVo, // value 映射
                        (existing, replacement) -> { // 合并策略
                            existing.setNumber(existing.getNumber() + replacement.getNumber());
                            return existing;
                        },
                        HashMap::new
                ));

        // 创建一个新的列表来存储去重后的 CartVo 对象
        List<CartVo> processedCartItems = new ArrayList<>(groupedCartItems.values());

        return processedCartItems;
    }

    // 自定义 key 生成器
    private CartVo createKey(CartVo cartVo) {
        CartVo key = new CartVo();
        key.setSetmealId(cartVo.getSetmealId());
        key.setDishId(cartVo.getDishId());
        return key;
    }

}



