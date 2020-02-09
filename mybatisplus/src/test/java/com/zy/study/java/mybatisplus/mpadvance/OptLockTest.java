package com.zy.study.java.mybatisplus.mpadvance;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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
public class OptLockTest {
    @Autowired
    private UsersMapper usersMapper;

    @Test
    public void updateById(){
        Users users = new Users();
        users.setId(1226155184401543169L);
        users.setEmail("bigboss@163.com");
        int rows = usersMapper.updateById(users);
        System.out.println("影响行数" + rows);
    }

    @Test
    public void updateById2(){
        //模拟预期库中这条数据的版本
        int version = 1;

        Users users = new Users();
        users.setId(1226155184401543169L);
        users.setVersion(version);
        users.setEmail("bigboss2@163.com");
        int rows = usersMapper.updateById(users);
        System.out.println("影响行数" + rows);
    }

    /*
    * wrapper复用演示
    * */
    @Test
    public void update(){
        //模拟预期库中这条数据的版本
        int version = 2;

        Users users = new Users();
        users.setVersion(version);
        users.setEmail("bigboss3@163.com");

        QueryWrapper<Users> queryWrapper = Wrappers.<Users>query();
        queryWrapper.eq("name","qianlong");

        int rows = usersMapper.update(users,queryWrapper);
        System.out.println("影响行数" + rows);

        int version2 = 3;

        Users users2 = new Users();
        users2.setVersion(version2);
        users2.setEmail("bigboss4@163.com");
        //复用wrapper
        queryWrapper.eq("age",37);
        int rows2 = usersMapper.update(users2,queryWrapper);
        System.out.println("影响行数" + rows2);

    }
}
