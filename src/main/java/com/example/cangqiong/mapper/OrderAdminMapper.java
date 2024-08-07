package com.example.cangqiong.mapper;


import com.example.cangqiong.dto.OrderAdminDto;
import com.example.cangqiong.dto.OrderSubmit;
import com.example.cangqiong.dto.TakeOrdersDto;
import com.example.cangqiong.vo.CartVo;
import com.example.cangqiong.vo.StatisticsVo;
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


    /**
     * 单个订单
     * @param orderId
     * @param id
     * @return
     */
    OrderSubmit getOrderDetails(String orderId, String storeId);


    /**
     * 查询订单详情
     */
    List<CartVo> getOrderCartVoDetails(String orderId);


    void manipulateOrders(TakeOrdersDto takeOrdersDto);

    /**
     *
     *
     *
     *
     * 统计订单数据
     * @param storeId
     * @param waitPay
     * @param accepted
     * @param acceptEd
     * @return
     */
    StatisticsVo getOrderStatistics(String storeId,
                                    String delivering,
                                    String waitAccept,
                                    String acceptEd,
                                    String cancelled,
                                    String completed
    );
}
