package com.zy.study.java.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.study.java.mybatisplus.entity.User;
import com.zy.study.java.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
class MpDeleteTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testDeleteById() {
        System.out.println(("----- testDeleteById method test ------"));
       int rows = userMapper.deleteById(1225361054423408641L);
       System.out.println("删除条数： " + rows);
    }

    @Test
    public void testDeleteByMap() {
        //map中的数据会作为where的条件，key是列名，value是值
        Map<String, Object> cloumnMap = new HashMap<>();
        cloumnMap.put("name","王八蛋");
        cloumnMap.put("age",27);
        int rows = userMapper.deleteByMap(cloumnMap);
        System.out.println("删除条数： " + rows);
    }

    @Test
    public void deletedBatchIdsTest() {
        int rows = userMapper.deleteBatchIds(Arrays.asList(2,4));
        System.out.println("删除条数： " + rows);
    }

    @Test
    public void deletedWrapperTest() {
        LambdaQueryWrapper<User> lambdaQueryWrapper = Wrappers.<User>lambdaQuery();
        lambdaQueryWrapper.eq(User::getName,"").or().lt(User::getAge,20);
        int rows = userMapper.delete(lambdaQueryWrapper);
        System.out.println("删除条数： " + rows);
    }
}
