package com.example.cangqiong.vo;


import com.example.cangqiong.dto.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * 分页VO
 * @param <T>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageVo<T> {
    private Long total;
    private List<T> records;
}
