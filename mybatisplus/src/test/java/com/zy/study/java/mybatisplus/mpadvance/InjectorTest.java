package com.zy.study.java.mybatisplus.mpadvance;

import com.zy.study.java.mybatisplus.entity.Users;
import com.zy.study.java.mybatisplus.mapper.UsersMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InjectorTest {

    @Autowired
    private UsersMapper usersMapper;

    @Test
    public void deleteAll(){
        int rows = usersMapper.deleteAll();
        System.out.println("影响行数" + rows);
    }

    @Test
    public void insertList(){
        Users users1 = new Users();
        users1.setName("小张");
        users1.setAge(23);

        Users users2 = new Users();
        users2.setName("小王");
        users2.setAge(28);
        usersMapper.insertBatchSomeColumn(Arrays.asList(users1,users2));
    }

    @Test
    public void alwaysUpdateSomeColumnByIdTest() {
        Users users1 = new Users();
        users1.setName("小张33");
        users1.setAge(28);
        users1.setId(1226474763656491010L);
        int rows = usersMapper.alwaysUpdateSomeColumnById(users1);
        System.out.println("影响行数" + rows);
    }

}
