package com.zy.study.java.mybatisplus.mpadvance;


import com.zy.study.java.mybatisplus.entity.Users;
import com.zy.study.java.mybatisplus.service.UsersService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataParperTest {
    @Autowired
    private UsersService usersService;

    @Test
    public void insert(){
        Users users = new Users();
        users.setName("qianlong");
        users.setAge(45);
        // new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
        users.setCreateTime(LocalDateTime.now());
        users.setUpdateTime(LocalDateTime.now());
        users.setEmail("qianlong@163.com");
        //usersService.save(users);
    }

    @Test
    public void insert2(){
        Users users = new Users();
        users.setName("经理2");
        users.setAge(33);
        users.setEmail("经理2@163.com");
        users.setManagerId(1226155184401543169L);
        usersService.save(users);
    }

    @Test
    public void insert3(){
        Users users1 = new Users();
        users1.setName("小张");
        users1.setAge(23);
        users1.setCreateTime(LocalDateTime.now());
        users1.setEmail("小张@163.com");
        users1.setManagerId(1226155974591565826L);

        Users users2 = new Users();
        users2.setName("小王");
        users2.setAge(28);
        users2.setCreateTime(LocalDateTime.now());
        users2.setEmail("小王@163.com");
        users2.setManagerId(1226155974591565826L);

        Users users3 = new Users();
        users3.setName("小李");
        users3.setAge(33);
        users3.setCreateTime(LocalDateTime.now());
        users3.setEmail("小李@163.com");
        users3.setManagerId(1226155974591565826L);
        //usersService.saveBatch(Arrays.asList(users1,users2,users3));
    }
}
