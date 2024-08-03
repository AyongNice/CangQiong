package com.example.cangqiong.mapper;


import com.example.cangqiong.dto.Address;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper
public interface AddressMapper {
//    @Update("update user set fingerprint_code=#{fingerprintCode} where user_id=#{userId}")
//    public Integer setFingerprint(String fingerprintCode, String userId);
//}


    public Integer addAddress(Address address);

    /**
     * cityCode: null
     * cityName: null
     * consignee: "阿勇"
     * detail: "长安街"
     * districtCode: null
     * districtName: null
     * id: 2
     * isDefault: 0
     * label: "2"
     * phone: "13240611891"
     * provinceCode: null
     * provinceName: null
     * sex: "0"
     * userId: null
     *
     * @param openId
     * @return
     */

    @Select("select id, user_id as userId," +
            "city_code as  cityCode ," +
            "city_name as  cityName ," +
            "consignee," +
            "detail," +
            "district_code as  districtCode , " +
            "district_name as  districtName ," +
            "is_default as  isDefault  , " +
            "label," +
            "phone," +
            "sex," +
            "province_code as  provinceCode ," +
            "province_name as   provinceName from address_book where user_id=#{openId}")
    public List<Address> getAddressList(String openId);


    //    @Update("UPDATE address_book SET " +
//            "city_code = #{cityCode}," +
//            "city_name = #{cityName}," +
//            "consignee = #{consignee}," +
//            "detail = #{detail}," +
//            "district_code = #{districtCode}," +
//            "district_name = #{districtName}," +
//            "is_default = #{isDefault}," +
//            "label = #{label}," +
//            "phone = #{phone}," +
//            "province_code = #{provinceCode}," +
//            "province_name = #{provinceName}," +
//            "sex = #{sex}" +
//            "WHERE id = #{id}")
    public Integer setAddressBook(Address address);


    /**
     * 设置默认地址
     *
     * @param id
     * @param openId
     * @return
     */
    @Update("update address_book set is_default=1 where  id=#{id} and user_id=#{openId}")
    Integer setDefaultAddress(String id, String openId);


    /**
     * 除这条地址以外都设置非默认
     *
     * @param id
     * @return
     */
    @Update("update address_book set is_default=0 where  id!=#{id} and user_id=#{openId}")
    Integer setFeiDefaultAddress(String id, String openId);


    @Select("select id, user_id as userId," +
            "city_code as  cityCode ," +
            "city_name as  cityName ," +
            "consignee," +
            "detail," +
            "district_code as  districtCode , " +
            "district_name as  districtName ," +
            "is_default as  isDefault  , " +
            "label," +
            "phone," +
            "sex," +
            "province_code as  provinceCode ," +
            "province_name as   provinceName from address_book where id=#{id}")
    Address getAddressCurrent(String id);
}
