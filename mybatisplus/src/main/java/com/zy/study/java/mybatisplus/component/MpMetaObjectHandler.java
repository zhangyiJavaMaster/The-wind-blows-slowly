package com.zy.study.java.mybatisplus.component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/*
* MP的自动填充器。处理users类的创建时间和更新时间的自动填充
* */
@Component
public class MpMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        // 判断对象的 createTime 是否有setter方法（或者是其他逻辑）
        boolean hasSetter = metaObject.hasSetter("createTime");
        // 若存在某字段的setter方法，就给他填充值
        if (hasSetter){
            System.out.println("====调用插入自动填充时间====");
            //给 createTime 属性添加当前时间为自动填充值
            setFieldValByName("createTime", LocalDateTime.now(),metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //若自己设置了更新时间，就不自动填充
        Object value= getFieldValByName("updateTime",metaObject);
        if (null == value){
            System.out.println("====调用更新入自动填充时间====");
            //给 updateTime 属性添加当前时间为自动填充值
            setFieldValByName("updateTime", LocalDateTime.now(),metaObject);
        }
    }
}
