package com.example.cangqiong.mapper;


import com.example.cangqiong.dto.OrderAdminDto;
import com.example.cangqiong.dto.OrderSubmit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderAdminMapper {

    /**
     * 管理端查询订单
     *
     * @param orderAdminDto
     * @return
     */
    List<OrderSubmit> getOrderPage(OrderAdminDto orderAdminDto);
}
