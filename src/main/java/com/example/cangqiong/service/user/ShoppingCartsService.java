package com.example.cangqiong.service.user;


import com.example.cangqiong.dto.CartDto;

import com.example.cangqiong.dto.DishDto;
import com.example.cangqiong.dto.SetmealDto;
import com.example.cangqiong.service.admin.DishService;
import com.example.cangqiong.service.admin.SetmealService;
import com.example.cangqiong.utlis.CartIdGenerator;
import com.example.cangqiong.utlis.FilePathToBase64;
import com.example.cangqiong.utlis.JwtUtil;
import com.example.cangqiong.vo.CartVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ShoppingCartsService {

    @Autowired
    private StringRedisTemplate strRedisT;


    @Autowired
    private DishService dishService;


    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private SetmealService setmealService;

    @Autowired
    private FilePathToBase64 filePathToBase64;


    public void add(CartDto cartDto, String authentication) {

        String dishId = cartDto.getDishId();

        String name = null;
        BigDecimal amount1 = null;
        String image = null;

        //存储菜品类型购物车
        if (dishId != null) {
            DishDto dishDto = dishService.getDishById(dishId, cartDto.getStoreId());
            //查询菜品价格
            amount1 = new BigDecimal(dishDto.getPrice());
            name = dishDto.getName();

            image = filePathToBase64.convertFilePathToBase64(dishDto.getImage());
        }

        //存储套餐类型 购物车
        if (cartDto.getSetmealId() != null) {
            SetmealDto setmealDto = setmealService.getSetmeal(cartDto.getSetmealId(), cartDto.getStoreId());
            name = setmealDto.getName();
            amount1 = new BigDecimal(setmealDto.getPrice());
            image = filePathToBase64.convertFilePathToBase64(setmealDto.getImage());
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

        //价格
        String field6 = "amount";
        String value6 = amount1 == null ? " " : amount1.toString();

        //名称
        String field7 = "name";
        String value7 = name == null ? " " : name;

        //图片
        String field8 = "image";
        String value8 = image == null ? " " : image;

        hashOps.put(key, field1, value1);
        hashOps.put(key, field2, value2);
        hashOps.put(key, field3, value3);
        hashOps.put(key, field4, value4);
        hashOps.put(key, field5, value5);
        hashOps.put(key, field6, value6);
        hashOps.put(key, field7, value7);
        hashOps.put(key, field8, value8);


        // 存储 userId 与 cartId 的关系
        setOps.add("userId:" + cartDto.getUserId(), key);

        // 存储 storeId 与 cartId 的关系
        setOps.add("storeId:" + cartDto.getStoreId(), key);


    }


    public List<CartVo> getShoppingCartList(String storeId, String authentication) {

        String userId = jwtUtil.getOpenId(authentication);

//        // 1. 获取所有以 "cart:" 开头的 key
        String[] cartKeys = Objects.requireNonNull(strRedisT.keys("cart:" + "*")).toArray(new String[0]);


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

        Object image = cartItemMap.get("image");
        if (image != null) {
            cartItem.setImage(String.valueOf(image));
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
        return new ArrayList<>(groupedCartItems.values());
    }

    // 自定义 key 生成器
    private CartVo createKey(CartVo cartVo) {
        CartVo key = new CartVo();
        key.setSetmealId(cartVo.getSetmealId());
        key.setDishId(cartVo.getDishId());
        return key;
    }


    /**
     * 清楚当前用户 某店铺 购物车
     *
     * @param storeId
     * @param authentication
     * @return
     */
    public void cleanShoppingCart(String storeId, String authentication) {
        String userId = jwtUtil.getOpenId(authentication);
        //清楚redis中 key中包含   cart:#{userId}:#{storeId} 的值
        strRedisT.delete(Objects.requireNonNull(strRedisT.keys("cart:" + storeId + userId + "*")));
    }

    /**
     * 购物车删除一条记录
     */
    public void deleteCart(CartDto cartDto, String authentication) {

        String userId = jwtUtil.getOpenId(authentication);

        //先找出 keys包含 cart:#{userId}:#{storeId} 的值

        String[] keys = Objects.requireNonNull(strRedisT.keys("cart:" + cartDto.getStoreId() + userId + "*")).toArray(new String[0]);


        String dishId = cartDto.getDishId();
        String setmealId = cartDto.getSetmealId();

        //找到这属于这些key的值 当值 中有  #{dishId} 或 #{setmealId} 等于 传入的  storeId 就删除 redis 这条key的数据
        for (String key : keys) {
            Map<Object, Object> cartItemMap = strRedisT.opsForHash().entries(key);
            for (Map.Entry<Object, Object> entry : cartItemMap.entrySet()) {
                //获取当前key的 字符
                Object value = entry.getValue();
                //通过key获取value
                String valueStr = String.valueOf(value);
                if (value != null && value != "" && valueStr != null) {

                    // 判断是否等于 传入的  dishId 菜品id
                    if (valueStr.equals(dishId)) {
                        strRedisT.delete(Objects.requireNonNull(strRedisT.keys(key)));
                        return;
                    }
                    // 判断是否等于 传入的  setmealId 套餐id
                    if (valueStr.equals(setmealId)) {
                        strRedisT.delete(Objects.requireNonNull(strRedisT.keys(key)));
                        return;

                    }
                }


            }
        }

        //抛出运行异常报错
        throw new RuntimeException("未找到该产品删除失败");


    }
}



