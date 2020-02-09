package com.zy.study.java.mybatisplus.mpadvance;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zy.study.java.mybatisplus.entity.Users;
import com.zy.study.java.mybatisplus.mapper.UsersMapper;
import com.zy.study.java.mybatisplus.until.TableNameUntil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LogicDeleteTest {

    @Autowired
    private UsersMapper usersMapper;

    @Test
    public void delete(){
        int rows = usersMapper.deleteById(1226157009917771777L);
        System.out.println("影响行数" + rows);
    }

    @Test
    public void select(){
        TableNameUntil.myTableName.set("users_2019");
        Users rows = usersMapper.selectById(1226157009917771777L);
        System.out.println("查询到的数据" + rows);
    }

    @Test
    public void selectList(){
        List<Users> rows = usersMapper.mySelectList(
                Wrappers.<Users>lambdaQuery()
                        .lt(Users::getAge,35)
                        .eq(Users::getDeleted,0));
        rows.forEach(System.out::println);
    }
}
