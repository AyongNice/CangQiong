package com.example.cangqiong.service;


import com.example.cangqiong.constant.TakeOrders;
import com.example.cangqiong.mapper.ReportMapper;
import com.example.cangqiong.utlis.JwtUtil;
import com.example.cangqiong.vo.TurnoverStatisticsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
