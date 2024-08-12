package com.example.cangqiong.service.admin;


import com.example.cangqiong.constant.TakeOrders;
import com.example.cangqiong.dto.OderUser;
import com.example.cangqiong.mapper.ReportMapper;
import com.example.cangqiong.utlis.JwtUtil;
import com.example.cangqiong.vo.TurnoverStatisticsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReportService {

    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    ReportMapper reportMapper;

    public TurnoverStatisticsVo getTurnoverStatistics(String token, String begin, String end) {
       return reportMapper.getTurnoverStatistics(jwtUtil.getID(token), TakeOrders.COMPLETED.getCode(),begin,end);

    }

    /**
     * 订单统计接口
     */

    public TurnoverStatisticsVo getOrderStatistics(String token, String begin, String end) {
        return reportMapper.getOrderStatistics(jwtUtil.getID(token),begin,end);
    }

    public TurnoverStatisticsVo getTop10(String token, String begin, String end) {
        return reportMapper.getTop10(jwtUtil.getID(token),begin,end);
    }

    public TurnoverStatisticsVo getUserStatistics(String token, String begin, String end) {
          List<OderUser> list  =  reportMapper.getUserStatistics(jwtUtil.getID(token),begin,end);

        /**
         *   统计 中每天订单数据  的新用户 数量 和 当前用户总量
         */
        // 统计结果
        // 示例订单数据
        List<OderUser> orders = Arrays.asList(
                new OderUser("u18", "2023-01-01"),
                new OderUser("u18", "2023-01-01"),
                new OderUser("u18", "2023-01-01"),
                new OderUser("u181", "2023-01-01"),
                new OderUser("u183", "2023-01-01"),
                new OderUser("u3", "2023-01-02"),
                new OderUser("u4", "2023-01-02"),
                new OderUser("u5", "2023-01-02"),
                new OderUser("u5", "2023-01-02") // 重复用户
        );

        // 统计结果
        Map<String, Set<String>> dailyNewUsers = new HashMap<>();

        Map<String, Integer> dailyToatlUsers = new HashMap<>();
        Map<String, Integer> dailyNewAddUsers = new HashMap<>();


        for (int i = 0; i < orders.size(); i++) {
            OderUser order = orders.get(i);
            OderUser orderOld = null;
            if (i > 1) {
                orderOld = orders.get(i - 1);
            }

            Set<String> totalUsers = new HashSet<>();

            Set<String> list = dailyNewUsers.get(order.getOrderTime());

            if (list != null) {
                list.add(order.getUserId());
                //获取  list 用户 长度
                dailyNewAddUsers.put(order.getOrderTime(), list.size());


                // 获取前一天
                if(orderOld != null){
                    Integer toalt = dailyNewAddUsers.get(orderOld.getOrderTime()) + list.size();

                    System.out.println(toalt+"--toalt-" + orderOld.getOrderTime());
                    dailyToatlUsers.put(order.getOrderTime(), toalt);
                }else {
                    // 获取第一天
                    dailyToatlUsers.put(order.getOrderTime(), totalUsers.size());
                }


            } else {
                totalUsers.add(order.getUserId());
                dailyNewUsers.put(order.getOrderTime(), totalUsers);
            }

        }




        // 输出结果
        System.out.println("每日---数量: " + dailyNewUsers);
        System.out.println("每日总用户数量: " + dailyToatlUsers);
        System.out.println("每日新用户数量: " + dailyNewAddUsers);

    }




        return null;
    }
}
