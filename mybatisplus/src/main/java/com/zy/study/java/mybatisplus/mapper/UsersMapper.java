package com.zy.study.java.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.zy.study.java.mybatisplus.entity.Users;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UsersMapper extends MyMapper<Users> {
    //@SqlParser(filter = true) 自定义的mySelectList 不增加租户信息为条件
    //@SqlParser(filter = true)
    @Select("select * from users ${ew.customSqlSegment}")
    List<Users> mySelectList(@Param(Constants.WRAPPER)Wrapper<Users> wrapper);
}
