package com.example.cangqiong.service.admin;


import com.example.cangqiong.constant.TakeOrders;
import com.example.cangqiong.entity.dto.OderUser;
import com.example.cangqiong.mapper.ReportMapper;
import com.example.cangqiong.utlis.JwtUtil;
import com.example.cangqiong.entity.vo.TurnoverStatisticsVo;
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
        return reportMapper.getTurnoverStatistics(jwtUtil.getID(token), TakeOrders.COMPLETED.getCode(), begin, end);

    }

    /**
     * 订单统计接口
     */

    public TurnoverStatisticsVo getOrderStatistics(String token, String begin, String end) {
        return reportMapper.getOrderStatistics(jwtUtil.getID(token), begin, end);
    }

    public TurnoverStatisticsVo getTop10(String token, String begin, String end) {
        return reportMapper.getTop10(jwtUtil.getID(token), begin, end);
    }

    public TurnoverStatisticsVo getUserStatistics(String token, String begin, String end) {
        List<OderUser> list = reportMapper.getUserStatistics(jwtUtil.getID(token), begin, end);

        TurnoverStatisticsVo vo = new TurnoverStatisticsVo();
        Map<String, Integer> dailyUniqueUsers = countDailyUniqueUsers(list);

        // 输出结果
        String dateList = convertToCommaSeparatedList(dailyUniqueUsers.keySet());
        vo.setDateList(dateList);
        String totalUserList = convertToCommaSeparatedList(accumulateDailyValues(dailyUniqueUsers).values());
        vo.setTotalUserList(totalUserList);
        String newUserList = convertToCommaSeparatedList(dailyUniqueUsers.values());
        vo.setNewUserList(newUserList);
        return vo;
    }

    /**
     *  统计每日 新增用户
     * @param orders
     * @return
     */
    private Map<String, Integer> countDailyUniqueUsers(List<OderUser> orders) {
        Map<String, Integer> dailyUniqueUsers = new LinkedHashMap<>();
        Set<String> countedUsers = new HashSet<>();

        for (OderUser order : orders) {
            String date = order.getOrderTime();
            String userId = order.getUserId();
            // 获取当前日期的用户计数
            Integer count = dailyUniqueUsers.get(date);

            // 如果是第一次出现该日期，则初始化计数为 1
            if (count == null) {
                dailyUniqueUsers.put(date, 1);

            } else {

                System.out.println(countedUsers);
                // 如果还没有统计过该用户，则计数加一
                System.out.println(countedUsers.contains(userId) + userId);
                if (!countedUsers.contains(userId)) {
                    dailyUniqueUsers.put(date, count + 1);
                }
            }
            // 获取或创建当前日期的已统计用户集
            countedUsers.add(userId);
        }

        return dailyUniqueUsers;
    }

    /**
     * 每日新增用户转换 当天总数
     * @param dailyValues
     * @return
     */
    private Map<String, Integer> accumulateDailyValues(Map<String, Integer> dailyValues) {
        // 创建一个新的 LinkedHashMap 来存储累积值
        Map<String, Integer> accumulatedValues = new LinkedHashMap<>();

        // 累加值
        int sum = 0;
        for (Map.Entry<String, Integer> entry : dailyValues.entrySet()) {
            sum += entry.getValue();
            accumulatedValues.put(entry.getKey(), sum);
        }

        return accumulatedValues;
    }


    /**
     * 将集合转换为逗号分隔的字符串
     * @param collection
     * @return
     */
    private String convertToCommaSeparatedList(Collection<?> collection) {
        StringBuilder sb = new StringBuilder();
        for (Object item : collection) {
            sb.append(item).append(",");
        }
        // 移除最后一个逗号
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }
}
