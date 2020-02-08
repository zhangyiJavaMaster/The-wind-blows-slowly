package com.zy.study.java.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.zy.study.java.mybatisplus.entity.User;
import com.zy.study.java.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MpUpdataTest {

    @Autowired
    private UserMapper userMapper;
    @Test
    public void testUpdataById() {
        System.out.println(("----- testUpdataById method test ------"));
        User user = new User();
        user.setId(1225257220019675137L);
        user.setAge(20);
        user.setEmail("33333333333333@163.com");
        int rows = userMapper.updateById(user);
        System.out.println(("更新条数 ：" + rows));
    }

    @Test
    public void testUpdataByWrapper() {
        System.out.println(("----- testUpdataByWrapper method test ------"));
        //1.创建UpdateWrapper，并组装匹配条件
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.eq("name","文Jack");
        //2.创建实体对象，封装要更新的数据
        User user = new User();
        user.setAge(40);
        user.setEmail("2222222222@163.com");
        //3.执行更新操作
        int rows = userMapper.update(user,userUpdateWrapper);
        System.out.println(("更新条数 ：" + rows));
    }

    @Test
    public void testUpdataByWrapper2() {
        System.out.println(("----- testUpdataByWrapper method test ------"));
        //1.创建UpdateWrapper，并组装匹配条件,并设置需要更新的字段
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        // 若只更新表中少量字段，通过对象封装需要更新的数据，很繁琐，可采用set方式
        userUpdateWrapper.eq("name","文Jack").set("age",32);
        //2.执行更新操作    第一个参数穿 null
        int rows = userMapper.update(null,userUpdateWrapper);
        System.out.println(("更新条数 ：" + rows));
    }

    @Test
    public void testUpdataByWrapperLambda() {
        System.out.println(("----- testUpdataByWrapperLambda method test ------"));
        //1.创建LambdaUpdateWrapper对象
        LambdaUpdateWrapper<User> lambdaUpdateWrapper = Wrappers.lambdaUpdate();
        //设置匹配条件，设置需要更新的数据。若更新多个列，可采用对象封装的方式。
        // 使用lambda方式构造wrapper条件，对列明的获取采用get方式能够有效防止写错。
        //手动写入列名比程序或者更容易出错，若列名发生变化，使用lambda方式不用修改代码
        lambdaUpdateWrapper.eq(User::getName,"文Jack")
                .set(User::getEmail,"666@163.com");

        //2.执行更新操作    第一个参数穿 null
        int rows = userMapper.update(null,lambdaUpdateWrapper);
        System.out.println(("更新条数 ：" + rows));
    }

    @Test
    public void testUpdataByWrapperLambda2() {
        System.out.println(("----- testUpdataByWrapperLambda2 method test ------"));
        //1.Lambda链式操作
        boolean isUpdata = new LambdaUpdateChainWrapper<User>(userMapper)
                .eq(User::getName,"文Jack")
                .set(User::getEmail,"777@163.com").update();
        System.out.println(("更新是否成功 ：" + isUpdata));
    }
}
