package com.example.cangqiong.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DishDto {

    /**
     * id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
     *   `name` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '菜品名称',
     *   `category_id` bigint NOT NULL COMMENT '菜品分类id',
     *   `price` decimal(10,2) DEFAULT NULL COMMENT '菜品价格',
     *   `image` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '图片',
     *   `description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '描述信息',
     *   `status` int DEFAULT '1' COMMENT '0 停售 1 起售',
     *   `create_time` datetime DEFAULT NULL COMMENT '创建时间',
     *   `update_time` datetime DEFAULT NULL COMMENT '更新时间',
     *   `create_user` bigint DEFAULT NULL COMMENT '创建人',
     *   `update_user` bigint DEFAULT NULL COMMENT '修改人',
     *   PRIMARY KEY (`id`),
     *   UNIQUE KEY `idx_dish_name` (`name`)
     */
    private String id;
    private String name;
    private String categoryId;
    private Integer price;
    private String image;
    private String description;
    private String status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String createUser;
    private String updateUser;
    private String categoryName;


}
