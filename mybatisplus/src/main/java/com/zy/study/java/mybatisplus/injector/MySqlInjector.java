package com.zy.study.java.mybatisplus.injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.extension.injector.methods.AlwaysUpdateSomeColumnById;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;
import com.baomidou.mybatisplus.extension.injector.methods.LogicDeleteByIdWithFill;
import com.zy.study.java.mybatisplus.method.DeleteAllMethod;
import org.springframework.stereotype.Component;

import java.util.List;

/*
* 自定义sql注入器 创建注入器
* 必须继承DefaultSqlInjector 或者 继承AbstractSqlInjector 或者 实现ISqlInjector接口
* 并且覆写 getMethodList 方法
* */
@Component
public class MySqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        //添加父类中已有的方法
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        //添加自定义的方法
        methodList.add(new DeleteAllMethod());
        //不是逻辑删除字段和年龄的进行插入
        methodList.add(new InsertBatchSomeColumn(
                t->!t.isLogicDelete() &&!"age".equals(t.getColumn())));
        methodList.add(new AlwaysUpdateSomeColumnById(
                t->!"name".equals(t.getColumn())));
        //new LogicDeleteByIdWithFill();

        return methodList;
    }
}
