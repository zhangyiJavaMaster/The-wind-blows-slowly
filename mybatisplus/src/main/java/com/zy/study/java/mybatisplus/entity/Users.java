package com.zy.study.java.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/*
* MP-高级 学习演示对应实体类
* */
@Data
@TableName("users")
public class Users extends Model<Users> {
    private Long id;
    private String name;
    private int age;
    private String email;
    private Long managerId;
    // java8 d的LocalDateTime
    //在插入时自定填充
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    //在更新时自动填充
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
    //version属性用于标记版本，用于乐观锁
    @Version
    private int version;
    //逻辑删除标识，0：未删除  1：已删除
    //@TableLogic 可以通过属性设置局部逻辑删除标识规则
    //@TableLogic(delval = 2)  //局部删除标识
    //@TableLogic(value = -2)  //局部未删除标识
    @TableLogic
    //@TableField(select = false) 表示查询中不显示
    @TableField(select = false)
    private int deleted;

}
