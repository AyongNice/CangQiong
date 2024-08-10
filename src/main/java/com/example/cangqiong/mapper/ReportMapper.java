package com.example.cangqiong.mapper;


import com.example.cangqiong.vo.TurnoverStatisticsVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReportMapper {
    TurnoverStatisticsVo getTurnoverStatistics(String storeId,String status, String begin, String end);

    TurnoverStatisticsVo getOrderStatistics(String id, String begin, String end);

    TurnoverStatisticsVo getTop10(String id, String begin, String end);
}