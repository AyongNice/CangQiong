package com.example.cangqiong.vo;


import com.example.cangqiong.dto.User;
import lombok.Data;

import java.util.List;

@Data
public class PageVo {
    private Integer total;
    private List<User> records;
}
