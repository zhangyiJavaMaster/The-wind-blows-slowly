package com.zy.study.java.mybatisplus.method;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

public class DeleteAllMethod extends AbstractMethod {
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {

        //自定义sql
        String sql = "delete from " + tableInfo.getTableName();
        //在mapper接口中方法的名字
        String method = "deleteAll";
        SqlSource sqlSource = languageDriver.createSqlSource(configuration,sql,modelClass);

        return addDeleteMappedStatement(mapperClass,method,sqlSource);
    }
}
