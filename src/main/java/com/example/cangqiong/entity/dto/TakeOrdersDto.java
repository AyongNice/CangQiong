package com.example.cangqiong.entity.dto;


import lombok.Data;

/**
 * 订单状态管理 String id, String rejectionReason, String storeId, String status
 */

@Data
public class TakeOrdersDto {

    private String id;
    private String rejectionReason;
    private String storeId;
    private String status;

}
