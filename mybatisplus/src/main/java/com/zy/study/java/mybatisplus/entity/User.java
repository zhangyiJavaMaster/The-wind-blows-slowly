package com.zy.study.java.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/*
* @Data import lombok.Data; 引入Lombok的注解
* @TableName("user") 指定实体类对应的表名
* @TableId 指定实体类中的字段对应表中的主键 ，主键通过雪花算法自动生成
* @TableField("name") 指定实体类中的属性对应表中的列明
*
*   MP 默认 主键为id，自增长，通过雪花算法生成值
*   MP 默认 实体类和表有 驼峰转下划线对应关系
* */
@Data
@TableName("user")
public class User extends Model<User> {
    //主键
    @TableId(type = IdType.AUTO)
    private Long id;
    //修改实体对象作为参数构造条件查询器时name属性默认为相等改成like条件匹配
    //@TableField(condition = SqlCondition.LIKE)
    private String name;

    //修改实体对象作为参数构造条件查询器时age属性默认为相等改成小于条件匹配
    //@TableField(condition = "%s&lt;#{%s}")
    private Integer age;
    @TableField("email")
    private String email;

    //备注
    //private transient String remark;
    @TableField(exist = false)
    private String remark;
    //private static String remark;
}
