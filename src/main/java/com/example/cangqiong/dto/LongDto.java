package com.example.cangqiong.dto;


import lombok.Data;

import java.io.Serializable;


@Data
public class LongDto implements Serializable {
    private String name;
    private String username;
    private Long id;
    private String password;

}
