package com.zy.study.java.mybatisplus.configuration;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.core.parser.ISqlParserFilter;
import com.baomidou.mybatisplus.core.parser.SqlParserHelper;
import com.baomidou.mybatisplus.extension.parsers.DynamicTableNameParser;
import com.baomidou.mybatisplus.extension.parsers.ITableNameHandler;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import com.zy.study.java.mybatisplus.until.TableNameUntil;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class MpAdvanceCofig {

    //配置MP的乐观锁插件
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor(){
        return new OptimisticLockerInterceptor();
    }


    //将MP分页插件添加到spring容器中,设置动态表名解析器
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        //创建一泛型为ISqlParser的list，ISqlParser为sql解析器接口
        ArrayList<ISqlParser> sqlParsers = new ArrayList<>();
        //创建动态表名解析器
        DynamicTableNameParser dynamicTableNameParser = new DynamicTableNameParser();
        Map<String, ITableNameHandler> tableNameHandlerMap = new HashMap<>();
        //map中，key为要替换的表名
        tableNameHandlerMap.put("users", new ITableNameHandler() {
            //返回值为替换后的表明
            @Override
            public String dynamicTableName(MetaObject metaObject, String sql, String tableName) {
               //模拟通过一定途径和方法获取的新的表名
               // String theTableName = "new_users";
                //从工具类的本地线程中获取表名
                String theTableName = TableNameUntil.myTableName.get();
                return theTableName;
            }
        });
        dynamicTableNameParser.setTableNameHandlerMap(tableNameHandlerMap);
        //将动态表名解析器添加到list中
        sqlParsers.add(dynamicTableNameParser);
        //将list设置到分页插件中
        paginationInterceptor.setSqlParserList(sqlParsers);
        //selectById方法不进行表名替换
        paginationInterceptor.setSqlParserFilter(new ISqlParserFilter() {
            @Override
            public boolean doFilter(MetaObject metaObject) {
                //获取方法名
                MappedStatement ms = SqlParserHelper.getMappedStatement(metaObject);
                String mt = ms.getId();
                //如果是usersmapper的selectById方法，则不进行表明替换
                if ("com.zy.study.java.mybatisplus.mapper.UsersMapper.selectById".equals(mt)){
                    //不替换表名
                    return true;
                }
                return false;
            }
        });
        return paginationInterceptor;
    }

    /*//将MP分页插件添加到spring容器中,设置多租户解析器
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        //创建一泛型为ISqlParser的list，ISqlParser为sql解析器接口
        ArrayList<ISqlParser> sqlParsers = new ArrayList<>();
        //创建租户sql解析器
        TenantSqlParser tenantSqlParser = new TenantSqlParser();

        tenantSqlParser.setTenantHandler(new TenantHandler() {

            //返回值为租户的信息，一般是从session或者配置文件中获取
            @Override
            public Expression getTenantId(boolean where) {
                //因为manager_id 为long类型
                // 创建的Expression接口的实现类对象类型要和字段类型对应
                return new LongValue(888888888L);
            }

            //返回值为多租户字段，表中的字段名
            @Override
            public String getTenantIdColumn() {
                return "manager_id";
            }

            //那些信息不加租户信息，入参为表名
            @Override
            public boolean doTableFilter(String tableName) {
                //示例：若为角色表，则不添加租户信息。
                if ("role_table".equals(tableName)){
                    return true;
                }
                return false;
            }
        });
        //将多租户sql解析器添加到list中
        sqlParsers.add(tenantSqlParser);
        //将list设置到分页插件中
        paginationInterceptor.setSqlParserList(sqlParsers);
        //特定SQL过滤，不添加租户信息
        paginationInterceptor.setSqlParserFilter(new ISqlParserFilter() {
            @Override
            public boolean doFilter(MetaObject metaObject) {
                //获取方法名
                MappedStatement ms = SqlParserHelper.getMappedStatement(metaObject);
                String mt = ms.getId();
                //如果是usersmapper的selectById方法，则不添加租户信息
                if ("com.zy.study.java.mybatisplus.mapper.UsersMapper.selectById".equals(mt)){
                    //不增加租户信息
                    return true;
                }
                return false;
            }
        });
        return paginationInterceptor;
    }*/
}
