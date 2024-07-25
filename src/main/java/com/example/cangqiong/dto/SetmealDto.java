package com.example.cangqiong.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetmealDto {

    /**
     * `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
     * `category_id` bigint NOT NULL COMMENT '菜品分类id',
     * `name` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '套餐名称',
     * `price` decimal(10,2) NOT NULL COMMENT '套餐价格',
     * `status` int DEFAULT '1' COMMENT '售卖状态 0:停售 1:起售',
     * `description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '描述信息',
     * `image` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '图片',
     * `create_time` datetime DEFAULT NULL COMMENT '创建时间',
     * `update_time` datetime DEFAULT NULL COMMENT '更新时间',
     * `create_user` bigint DEFAULT NULL COMMENT '创建人',
     * `update_user` bigint DEFAULT NULL COMMENT '修改人',
     * PRIMARY KEY (`id`),
     * UNIQUE KEY `idx_setmeal_name` (`name`)
     */

    private String createUser;
    private String description;
    private String image;
    private String name;
    private Integer status;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
    private Integer price;
    private String updateUser;
    private Long id;
    private Integer categoryId;
    private String  categoryName;
    private String createName;
    private List<SetmealDishesDto> setmealDishes;
}
