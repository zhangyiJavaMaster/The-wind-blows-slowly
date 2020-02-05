package com.zy.study.java.mybatisplus.entity;

import lombok.Data;

// @Data import lombok.Data; 引入Lombok的注解
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
