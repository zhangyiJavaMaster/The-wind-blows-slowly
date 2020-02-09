package com.zy.study.java.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MyMapper<T> extends BaseMapper<T> {

    /*
     * 删除所有
     * @return 影响行数
     * */
    //方法名为在DeleteAllMethod类中定义的方法名
    int deleteAll();

    /*
    * 批量新增数据，自选字段insert
    * */
    int insertBatchSomeColumn(List<T> list);

    int alwaysUpdateSomeColumnById(@Param(Constants.ENTITY)T entity);
}
