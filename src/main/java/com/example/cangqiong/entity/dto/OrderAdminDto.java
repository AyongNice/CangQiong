package com.example.cangqiong.entity.dto;


import lombok.Data;

@Data
public class OrderAdminDto {
    /**
     * beginTime	否		beginTime
     * endTime	否		endTime
     * number	否		number
     * page	是		page
     * pageSize	是		pageSize
     * phone	否		phone
     * status	否		status
     */
    private String beginTime;
    private String endTime;
    private String number;
    private Integer page;
    private Integer pageSize;
    private String phone;
    private String status;
    private String storeId;

}
