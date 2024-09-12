package com.example.cangqiong.entity.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartVo {
    /**
     * ├─ amount	number	非必须
     * ├─ createTime	string	非必须
     * format: date-time
     * <p>
     * ├─ dishFlavor	string	非必须
     * ├─ dishId	integer	非必须
     * format: int64
     * <p>
     * ├─ id	integer	非必须
     * format: int64
     * <p>
     * ├─ image	string	非必须
     * ├─ name	string	非必须
     * ├─ number	integer	非必须
     * format: int32
     * <p>
     * ├─ setmealId	integer	非必须
     * format: int64
     * <p>
     * ├─ userId	integer	非必须
     * format: int64
     */

    private String id;
    private String dishId;
    private String setmealId;
    private String dishFlavor;
    private String name;
    private String image;
    private Integer amount;
    private Integer number = 1;
    private String createTime;

    private String storeId;

    //订单Id
    private String orderId;

    public CartVo(CartVo cartVo) {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartVo cartVo = (CartVo) o;
        return Objects.equals(setmealId, cartVo.setmealId) &&
                Objects.equals(dishId, cartVo.dishId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(setmealId, dishId);
    }
}
