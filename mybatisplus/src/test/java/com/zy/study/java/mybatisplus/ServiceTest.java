package com.zy.study.java.mybatisplus;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zy.study.java.mybatisplus.entity.User;
import com.zy.study.java.mybatisplus.service.MpService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class ServiceTest {
    @Autowired
    private MpService mpService;

    @Test
    public void getOne() {
        //添加第二个参数为false，查询出来多个数据，取第一条，不报错
       User one = mpService.getOne(Wrappers.<User>lambdaQuery()
               .gt(User::getAge,25),false);
        System.out.println("查到数据： " + one);
    }

    @Test
    public void chain() {
        List<User> userList = mpService.lambdaQuery()
                .gt(User::getAge,25)
                .like(User::getName,"王")
                .list();
        userList.forEach(System.out::println);
    }

    @Test
    public void chain2() {
        boolean res = mpService.lambdaUpdate()
                .eq(User::getAge,25)
                .set(User::getEmail,"77@3.com")
                .update();
        System.out.println("更新结果 " + res);
    }

    @Test
    public void chain3() {
        boolean res = mpService.lambdaUpdate()
                .eq(User::getAge,25)
               .remove();
        System.out.println("删除结果 " + res);
    }

}
