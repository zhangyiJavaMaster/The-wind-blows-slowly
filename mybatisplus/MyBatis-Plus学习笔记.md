MyBatis-Plus官网 https://mp.baomidou.com/#/
****
一.简单入门:

 1.数据准备
 
   1）创建表
   
    DROP TABLE IF EXISTS user;
   
    CREATE TABLE user
    (
    	id BIGINT(20) NOT NULL COMMENT '主键ID',
    	name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
    	age INT(11) NULL DEFAULT NULL COMMENT '年龄',
    	email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
    	PRIMARY KEY (id)
    );
    
   2）插入数据
   
    DELETE FROM user;
    
    INSERT INTO user (id, name, age, email) VALUES
    (1, 'Jone', 18, 'test1@baomidou.com'),
    (2, 'Jack', 20, 'test2@baomidou.com'),
    (3, 'Tom', 28, 'test3@baomidou.com'),
    (4, 'Sandy', 21, 'test4@baomidou.com'),
    (5, 'Billie', 24, 'test5@baomidou.com');
    
2.添加pom依赖

            <!-- spring boot 的web启动器-->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-web</artifactId>
            </dependency>
    
            <!--springboot单元测试依赖-->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-test</artifactId>
                <scope>test</scope>
            </dependency>
    
            <!-- spring boot的启动器-->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter</artifactId>
            </dependency>
    
            <!-- lombok的依赖:自动生成getter和setter -->
            <dependency>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
                <optional>true</optional>
            </dependency>
            <!-- mybatis-plus 启动器的依赖 -->
            <dependency>
                <groupId>com.baomidou</groupId>
                <artifactId>mybatis-plus-boot-starter</artifactId>
                <version>3.3.1.tmp</version>
            </dependency>
            <!--mysql驱动-->
            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
            </dependency>
            <!--druid数据库连接池-->
            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>druid</artifactId>
                <version>1.1.8</version>
            </dependency>
            
3.在application.yml配置文件配置数据库连接和数据源

    spring:
      datasource:
        driver-class-name: com.mysql.jdbc.Driver
        url: jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT
        username: root
        password: 123456
        
4.代码编写

   1）在主启动类添加注解
   
        package com.zy.study.java.mybatisplus;
        
        import org.mybatis.spring.annotation.MapperScan;
        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
        
        @SpringBootApplication
        //开启mapper扫描
        @MapperScan("com.zy.study.java.mybatisplus.mapper")
        public class MybatisplusApplication {
        
            public static void main(String[] args) {
                SpringApplication.run(MybatisplusApplication.class, args);
            }
        }
        
   2）编写实体类
   
        package com.zy.study.java.mybatisplus.entity;
        
        import lombok.Data;
        
        // @Data import lombok.Data; 引入Lombok的注解
        @Data
        public class User {
            private Long id;
            private String name;
            private Integer age;
            private String email;
        }
        
   3）编写mapper接口
   
        package com.zy.study.java.mybatisplus.mapper;
        
        import com.baomidou.mybatisplus.core.mapper.BaseMapper;
        import com.zy.study.java.mybatisplus.entity.User;
        
        public interface UserMapper extends BaseMapper<User> {
        }
        
   4）编写测试类
   
        package com.zy.study.java.mybatisplus;
        
        import com.zy.study.java.mybatisplus.entity.User;
        import com.zy.study.java.mybatisplus.mapper.UserMapper;
        import org.junit.Assert;
        import org.junit.jupiter.api.Test;
        import org.junit.runner.RunWith;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.test.context.junit4.SpringRunner;
        
        import java.util.List;
        
        @RunWith(SpringRunner.class)
        @SpringBootTest
        class MybatisplusApplicationTests {
        
            @Autowired
            private UserMapper userMapper;
            @Test
            public void testSelect() {
                System.out.println(("----- selectAll method test ------"));
                List<User> userList = userMapper.selectList(null);
                Assert.assertEquals(5, userList.size());
                userList.forEach(System.out::println);
            }
        }
   5）运行结果
   
        2020-02-05 21:48:16.118  INFO 4132 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
        User(id=1, name=Jone, age=18, email=test1@baomidou.com)
        User(id=2, name=Jack, age=20, email=test2@baomidou.com)
        User(id=3, name=Tom, age=28, email=test3@baomidou.com)
        User(id=4, name=Sandy, age=21, email=test4@baomidou.com)
        User(id=5, name=Billie, age=24, email=test5@baomidou.com)
        2020-02-05 21:48:16.350  INFO 4132 --- [extShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown initiated...
        2020-02-05 21:48:16.410  INFO 4132 --- [extShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown completed.
        2020-02-05 21:48:16.411  INFO 4132 --- [extShutdownHook] o.s.s.concurrent.ThreadPoolTaskExecutor  : Shutting down ExecutorService 'applicationTaskExecutor'
        