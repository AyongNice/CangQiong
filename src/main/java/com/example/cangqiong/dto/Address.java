package com.example.cangqiong.dto;


import lombok.Data;

@Data
public class Address {

    /**
     * cityCode	string	非必须
     * cityName	string	非必须
     * consignee	string	非必须
     * detail	string	必须		详细地址
     * districtCode	string	非必须
     * districtName	string	非必须
     * id	integer	非必须
     * format: int64
     *
     * isDefault	integer	非必须
     * format: int32
     *
     * label	string	非必须
     * phone	string	必须		手机号
     * provinceCode	string	非必须
     * provinceName	string	非必须
     * sex	string	必须
     * userId
     */
    private String cityCode;
    private String cityName;
    private String consignee;
    private String detail;
    private String districtCode;
    private String districtName;
    private Integer id;
    private Integer isDefault=0;
    private String label;
    private String phone;
    private String provinceCode;
    private String provinceName;
    private String sex;
    private String userId;



}
