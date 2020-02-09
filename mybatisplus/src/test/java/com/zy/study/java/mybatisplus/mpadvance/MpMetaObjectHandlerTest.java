package com.zy.study.java.mybatisplus.mpadvance;

import com.zy.study.java.mybatisplus.entity.Users;
import com.zy.study.java.mybatisplus.mapper.UsersMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/*
* 自动填充测试类
* */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MpMetaObjectHandlerTest {
    @Autowired
    private UsersMapper usersMapper;

    @Test
    public void insert(){
        Users users = new Users();
        users.setName("经理3");
        users.setAge(33);
        users.setEmail("经理3@163.com");
        users.setManagerId(1226155184401543169L);
        int rows = usersMapper.insert(users);
        System.out.println("影响行数" + rows);
    }

    @Test
    public void update(){
        Users users = new Users();
        users.setId(1226187116824797186L);
        users.setName("经理2");
        users.setEmail("经理2@163.com");
        int rows = usersMapper.updateById(users);
        System.out.println("影响行数" + rows);
    }

    @Test
    public void selectById(){
        Users rows = usersMapper.selectById(1226187116824797186L);
        System.out.println("数据" + rows);

        Users one = new Users();
        one.setId(1226187116824797186L);
        one.setEmail("xxxx@163.com");
        int def = usersMapper.updateById(one);
        System.out.println("影响行数" + def);
    }
}
