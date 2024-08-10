package com.example.cangqiong.controller.admin;


import com.example.cangqiong.service.ReportService;
import com.example.cangqiong.utlis.Result;
import com.example.cangqiong.vo.TurnoverStatisticsVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/admin/report")
public class ReportController {


    @Autowired
    private ReportService reportService;

    /**
     * 营业额统计接口
     * begin=2024-08-09&end=2024-08-09
     */
    @GetMapping("/turnoverStatistics")
    public Result<TurnoverStatisticsVo> getTurnoverStatistics(@Param("begin") String begin, @Param("end")  String end, @RequestHeader("Token") String token) {

        return Result.success(reportService.getTurnoverStatistics(token,begin,end));
    }

    /**
     * 订单统计接口
     */

    @GetMapping("/ordersStatistics")
    public Result<TurnoverStatisticsVo> getOrderStatistics(@Param("begin") String begin, @Param("end")  String end, @RequestHeader("Token") String token) {

        return Result.success(reportService.getOrderStatistics(token,begin,end));
    }

    /**
     * top10 菜品统计
     */
    @GetMapping("/top10")
    public Result<TurnoverStatisticsVo> getTop10(@Param("begin") String begin, @Param("end")  String end, @RequestHeader("Token") String token) {

        return Result.success(reportService.getTop10(token,begin,end));
    }

}
