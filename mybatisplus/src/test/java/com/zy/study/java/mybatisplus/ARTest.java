package com.zy.study.java.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zy.study.java.mybatisplus.entity.User;
import com.zy.study.java.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
class ARTest {

    @Test
    public void insert() {
        User one = new User();
        one.setAge(18);
        one.setEmail("123@123.com");
        one.setName("萧然");
        //不使用mapper操作，对象自己操作
        boolean insert = one.insert();
        System.out.println("是否插入成功： " + insert);
    }

    @Test
    public void selectById() {
        User one = new User();
        //不使用mapper操作，对象自己操作
        User res = one.selectById(1226031978071990273L);
        System.out.println("元对象和新对象是否相等： " + one.equals(res));
        System.out.println("查询的数据： " + res);
    }

    @Test
    public void selectById2() {
        User one = new User();
        one.setId(1226031978071990273L);
        //不使用mapper操作，对象自己操作
        User res = one.selectById();
        System.out.println("原对象和新对象是否是一个对象： " + one.equals(res));
        System.out.println("查询的数据： " + res);
    }

    @Test
    public void updateByIdTest() {
        User one = new User();
        one.setId(1226031978071990273L);
        one.setEmail("qwer@5.com");
        //不使用mapper操作，对象自己操作
        boolean res = one.updateById();
        System.out.println("更新结果： " + res);
    }

    @Test
    public void deleteByIdTest() {
        User one = new User();
        one.setId(1226031978071990273L);
        //不使用mapper操作，对象自己操作
        boolean res = one.deleteById();
        System.out.println("删除结果： " + res);
    }

    @Test
    public void insertOrUodata() {
        User one = new User();
        one.setId(1226038156425829889L);
        one.setAge(18);
        one.setEmail("123@123.com");
        one.setName("萧然2");
        //不使用mapper操作，对象自己操作
        boolean insert = one.insertOrUpdate();
        System.out.println("是否插入成功： " + insert);
    }

}
