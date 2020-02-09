package com.zy.study.java.mybatisplus.mpbase;

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
class MpSelectTest {

    @Autowired
    private UserMapper userMapper;
    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        //Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

    @Test
    public void testInsert() {
        System.out.println(("----- Insert method test ------"));
        User one = new User();
        one.setAge(35);
        one.setEmail("123@123.com");
        one.setName("王9蛋");
        int rows = userMapper.insert(one);
        System.out.println("被影响的行数： " + rows);
    }

    /*
    * 根据id查询
    * */
    @Test
    public void selectById() {
        System.out.println(("----- SelectById method test ------"));
        int id = 5;
        User one = userMapper.selectById(id);
        System.out.println("查询到id为 "+ id +" 的数据为" + one.toString());
    }

    /*
     * 根据ids批量查询
     * */
    @Test
    public void selectByIds() {
        System.out.println(("----- SelectByIds method test ------"));
        List<Integer> ids = Arrays.asList(1,3,5);
        List<User> ones = userMapper.selectBatchIds(ids);
        System.out.println("查询到的数据为" + ones);
    }

    /*
     * and 多条件查询
     * */
    @Test
    public void selectByMap() {
        System.out.println(("----- selectByMap method test ------"));
        Map<String,Object> columnMap = new HashMap<>();
        columnMap.put("name","Tom");
        columnMap.put("age",28);
        List<User> ones = userMapper.selectByMap(columnMap);
        System.out.println("查询到的数据为" + ones);
    }

    /*
     * and 多条件查询2
     * */
    @Test
    public void selectByMap2() {
        System.out.println(("----- selectByMap method test ------"));
        Map<String,Object> columnMap = new HashMap<>();
        columnMap.put("age",28);
        List<User> ones = userMapper.selectByMap(columnMap);
        ones.forEach(System.out::println);
    }

    /*
     * 条件构造器查询
     * 名字中包含“八”，并且年龄小于40
     * */
    @Test
    public void selectByWrapper() {
        System.out.println(("----- selectByWrapper method test ------"));
        //创建条件构造器方式1
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //创建条件构造器方式2
        //QueryWrapper<User> query = Wrappers.query();

        //like,lt 等方法第一个参数均为表中列名
        queryWrapper.like("name","八").lt("age",40);

        List<User> ones = userMapper.selectList(queryWrapper);
        ones.forEach(System.out::println);
    }

    /*
     * 名字中包含八，并且年龄大于等于20且小于等于40，且Email不为空
     * */
    @Test
    public void selectByWrapper2() {
        System.out.println(("----- selectByWrapper method test ------"));
        //创建条件构造器方式1
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        queryWrapper.like("name","八").
                between("age",20,40).
                isNotNull("email");

        List<User> ones = userMapper.selectList(queryWrapper);
        ones.forEach(System.out::println);
    }

    /*
     * 名字为王姓或者年龄大于等于25，按照年龄降序排列，年龄相同按照id升序排列
     * */
    @Test
    public void selectByWrapper3() {
        System.out.println(("----- selectByWrapper method test ------"));
        //创建条件构造器方式1
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        queryWrapper.likeRight("name","王").
                or().ge("age",25).
                orderByDesc("age").orderByAsc("id");

        List<User> ones = userMapper.selectList(queryWrapper);
        ones.forEach(System.out::println);
    }

    /*
     * 年龄为35并且姓王
     * 涉及子查询
     * */
    @Test
    public void selectByWrapper4() {
        System.out.println(("----- selectByWrapper method test ------"));
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("age",35).
                inSql("name","select name from user where name like '王%'");

        List<User> ones = userMapper.selectList(queryWrapper);
        ones.forEach(System.out::println);
    }

    /*
     * 姓王并且（年龄小于40或邮箱不能为空）
     * WHERE (name LIKE ? AND (age < ? OR email IS NOT NULL))
     * */
    @Test
    public void selectByWrapper5() {
        System.out.println(("----- selectByWrapper method test ------"));
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        queryWrapper.likeRight("name","王").
                and(wq->wq.lt("age",40).or().isNotNull("email"));

        List<User> ones = userMapper.selectList(queryWrapper);
        ones.forEach(System.out::println);
    }

    /*
     * 姓王或者（年龄小于40并且年龄大于20并且邮箱不为空）
     * name LIKE ? OR (age < ? AND age > ? AND email IS NOT NULL)
     * */
    @Test
    public void selectByWrapper6() {
        System.out.println(("----- selectByWrapper method test ------"));
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        queryWrapper.likeRight("name","王")
                .or(wq->wq.lt("age",40)
                        .gt("age",20)
                        .isNotNull("email"));

        List<User> ones = userMapper.selectList(queryWrapper);
        ones.forEach(System.out::println);
    }

    /*
     * (年龄<40或邮箱不为空)并且姓王
     * (age < ? AND email IS NOT NULL) AND name LIKE ?
     * */
    @Test
    public void selectByWrapper7() {
        System.out.println(("----- selectByWrapper method test ------"));
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        queryWrapper.nested(wq->wq.lt("age",40)
                .isNotNull("email"))
                .likeRight("name","王");

        List<User> ones = userMapper.selectList(queryWrapper);
        ones.forEach(System.out::println);
    }

    /*
     * 年龄为30，31，34，35
     *  age IN (?,?,?,?)
     * */
    @Test
    public void selectByWrapper8() {
        System.out.println(("----- selectByWrapper method test ------"));
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        queryWrapper.in("age",Arrays.asList(30,31,34,35));
        List<User> ones = userMapper.selectList(queryWrapper);
        ones.forEach(System.out::println);
    }

    /*
     * 只返回满足条件的其中一条数据
     *  limit 1
     * */
    @Test
    public void selectByWrapper9() {
        System.out.println(("----- selectByWrapper method test ------"));
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //last 慎用，有sql注入风险，多次调用以最后一次调用为准
        queryWrapper.in("age",Arrays.asList(30,31,34,35)).last("limit 1");
        List<User> ones = userMapper.selectList(queryWrapper);
        ones.forEach(System.out::println);
    }

    /*
     * 条件构造器查询
     * 名字中包含“八”，并且年龄小于40
     * 只查询name和id列的数据
     * */
    @Test
    public void selectByWrapperSuper() {
        System.out.println(("----- selectByWrapperSuper method test ------"));
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id","name")
                .like("name","八")
                .lt("age",40);
        List<User> ones = userMapper.selectList(queryWrapper);
        ones.forEach(System.out::println);
    }

    /*
     * 条件构造器查询
     * 名字中包含“八”，并且年龄小于40
     * 不查询name和age的数据，剩下的都查
     * */
    @Test
    public void selectByWrapperSuper2() {
        System.out.println(("----- selectByWrapperSuper method test ------"));
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(User.class, tableinfo->!tableinfo.getColumn().equals("age")&&
                !tableinfo.getColumn().equals("name"))
                .like("name","八")
                .lt("age",40);
        List<User> ones = userMapper.selectList(queryWrapper);
        ones.forEach(System.out::println);
    }

    @Test
    public void testCondition() {
       String name = "李";
       String email = "";
       condition(name, email);
    }
    private void condition(String name, String email){
        System.out.println(("----- condition method test ------"));
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        /* 这种写法不够优雅
        //name 不为空，将name条件拼装到sql中
        if (!StringUtils.isEmpty(name)){
            queryWrapper.like("name",name);
        }
        //email 不为空，将email条件拼装到sql中
        if (!StringUtils.isEmpty(email)){
            queryWrapper.like("email",email);
        }*/
        queryWrapper.like(!StringUtils.isEmpty(name),"name",name)
                .like(!StringUtils.isEmpty(email),"email",email);

        List<User> ones = userMapper.selectList(queryWrapper);
        ones.forEach(System.out::println);
    }

    /*
     * 实体类作为条件构造器的参数
     * 名字中包含“八”，并且年龄小于40
     * */
    @Test
    public void selectByWrapperSuperEntity() {
        System.out.println(("----- selectByWrapperSuperEntity method test ------"));

        User one = new User();
        one.setName("王八蛋");
        one.setAge(35);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(one);

        List<User> ones = userMapper.selectList(queryWrapper);
        ones.forEach(System.out::println);
    }

    /*
     * 实体类作为条件构造器的参数,和like等方法的条件无关联关系
     * 名字中包含“八”，并且年龄小于40
     * */
    @Test
    public void selectByWrapperSuperEntity2() {
        System.out.println(("----- selectByWrapperSuperEntity2 method test ------"));

        User one = new User();
        one.setName("王八蛋");
        one.setAge(35);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(one);
        queryWrapper.like("name", "张");

        List<User> ones = userMapper.selectList(queryWrapper);
        ones.forEach(System.out::println);
    }

    /*
     * AllEq 用法
     * */
    @Test
    public void selectByWrapperAllEq() {
        System.out.println(("----- selectByWrapperAllEq method test ------"));
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        Map<String, Object> params = new HashMap<>();

        // 场景1：
        // 运行结果：
        // DEBUG==>  Preparing: SELECT id,name,age,email FROM user WHERE (name = ? AND age = ?)
        // DEBUG==> Parameters: 王八蛋(String), 35(Integer)
        // params.put("name","王八蛋");
        // params.put("age",35);
        // queryWrapper.allEq(params);

        // 场景2：
        // 默认，若有一个值为null。则在sql中拼接isNull
        // 运行结果：
        // DEBUG==>  Preparing: SELECT id,name,age,email FROM user WHERE (name = ? AND age IS NULL)
        // DEBUG==> Parameters: 王八蛋(String)
        //params.put("name","王八蛋");
        //params.put("age",null);
        //queryWrapper.allEq(params);

        // 场景3：
        //若为null，则参数不拼接
        //运行结果：
        //DEBUG==>  Preparing: SELECT id,name,age,email FROM user WHERE (name = ?)
        //DEBUG==> Parameters: 王八蛋(String)
        //params.put("name","王八蛋");
        //params.put("age",null);
        //allEq 传入两个参数，第二个参数为Boolean类型，默认为true
        // true：为null也拼接  false：为null不拼接
        //queryWrapper.allEq(params, false);

        //场景4：
        //运行结果：
       // DEBUG==>  Preparing: SELECT id,name,age,email FROM user WHERE (age IS NULL)
       // DEBUG==> Parameters:
        params.put("name","王八蛋");
        params.put("age",null);
        // 第一个参数为过滤器，判断map键值对要不要加入到where条件中，若key为name，则不加入到条件中
        //还可以通过值过滤，通过值过滤要考虑类型， 还可以再传第三个参数：是否要把null值拼在条件中
        queryWrapper.allEq((k,v)->!k.equals("name"),params);

        List<User> ones = userMapper.selectList(queryWrapper);
        ones.forEach(System.out::println);
    }

    /*
    * map查询
    * 应用场景：
    * 1.当表中字段特别多，只需要使用其中几列数据
    * 2.返回统计结果
    * */
    @Test
    public void selectByWrapperMaps() {
        System.out.println(("----- selectByWrapperMaps method test ------"));
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name","email").like("name","王").lt("age",35);
        List<Map<String, Object>> ones = userMapper.selectMaps(queryWrapper);
        ones.forEach(System.out::println);
    }

    /*
     * map查询
     * 应用场景：返回统计结果
     * 按照姓分组，查询每组中的平均年龄，最大年龄，和最小年龄
     * 并且只取年龄总和小于300的组
     * */
    @Test
    public void selectByWrapperMaps2() {
        System.out.println(("----- selectByWrapperMaps2 method test ------"));
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 其中 avg_age，min_age，max_age 均为别名
        queryWrapper.select("avg(age) avg_age", "min(age) min_age", "max(age) max_age")
                .groupBy("name")
                .having("sum(age)<{0}", 300);
        List<Map<String, Object>> ones = userMapper.selectMaps(queryWrapper);
        ones.forEach(System.out::println);
    }

    /*
     * selectObjs
     * */
    @Test
    public void selectByWrapperSelectObjs() {
        System.out.println(("----- selectByWrapperSelectObjs method test ------"));
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id","name")
                .like("name","八")
                .lt("age",40);
        List<Object> ones = userMapper.selectObjs(queryWrapper);
        ones.forEach(System.out::println);
    }

    /*
     * selectCount
     * */
    @Test
    public void selectByWrapperSelectCount() {
        System.out.println(("----- selectByWrapperSelectCount method test ------"));
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name","八")
                .lt("age",40);
        int one = userMapper.selectCount(queryWrapper);
        System.out.println("记录数 ： " + one);
    }

    /*
     * selectCount
     * */
    @Test
    public void selectByWrapperSelectOne() {
        System.out.println(("----- selectByWrapperSelectOne method test ------"));
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name","王八蛋")
                .lt("age",40);
        User one = userMapper.selectOne(queryWrapper);
        System.out.println("数据 ： " + one);
    }

    /*
     * selectByLambda
     * */
    @Test
    public void selectByLambda() {
        System.out.println(("----- selectByLambda method test ------"));
        //lambda 条件构造器的生成方式
        //方式1：
        //LambdaQueryWrapper<User> lambda = new QueryWrapper<User>().lambda();
        //方式2：
        //LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //方式3：
        LambdaQueryWrapper<User> lambdaQuery = Wrappers.<User>lambdaQuery();

        lambdaQuery.like(User::getName,"王").lt(User::getAge,40);
        List<User> ones = userMapper.selectList(lambdaQuery);
        ones.forEach(System.out::println);
    }

    /*
    * 姓王，且年（龄小于40或者邮箱不为空）
    * */
    @Test
    public void selectByLambda2() {
        System.out.println(("----- selectByLambda2 method test ------"));
        LambdaQueryWrapper<User> lambdaQuery = Wrappers.<User>lambdaQuery();
        lambdaQuery.likeRight(User::getName,"王")
                .and(lqw->lqw.lt(User::getAge,40)
                        .or().isNotNull(User::getEmail));
        List<User> ones = userMapper.selectList(lambdaQuery);
        ones.forEach(System.out::println);
    }


    @Test
    public void selectByLambda3() {
        System.out.println(("----- selectByLambda3 method test ------"));
        //LambdaQueryChainWrapper 实现 ChainQuery接口，
        //该接口中有list(),one(),count()等方法发
        List<User> ones = new LambdaQueryChainWrapper<User>(userMapper)
                .like(User::getName,"王").ge(User::getAge,20).list();
        ones.forEach(System.out::println);
    }


    //分页查询
    @Test
    public void selectPage() {
        System.out.println(("----- selectPage method test ------"));
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lt("age",40);
        //创建分页信息 查第1页，每页3条
        Page page = new Page<User>(1,3);
        IPage<User> iPage = userMapper.selectPage(page,queryWrapper);
        //查询到的数据
        List<User> users = iPage.getRecords();

        System.out.println("总页数 :" + iPage.getPages());
        System.out.println("总记录数 :" + iPage.getTotal());
        users.forEach(System.out::println);
    }

    //分页查询
    @Test
    public void selectPage2() {
        System.out.println(("----- selectPage2 method test ------"));
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lt("age",40);
        //创建分页信息 查第1页，每页3条
        Page page = new Page<User>(1,3);
        IPage<Map<String, Object>> iPage = userMapper.selectMapsPage(page,queryWrapper);
        //查询到的数据
        List<Map<String, Object>> users = iPage.getRecords();

        System.out.println("总页数 :" + iPage.getPages());
        System.out.println("总记录数 :" + iPage.getTotal());
        users.forEach(System.out::println);
    }

    //分页查询
    @Test
    public void selectPage3() {
        System.out.println(("----- selectPage3 method test ------"));
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lt("age",40);
        //创建分页信息 查第1页，每页3条
        Page page = new Page<User>(1,3,false);
        IPage<Map<String, Object>> iPage = userMapper.selectMapsPage(page,queryWrapper);
        //查询到的数据
        List<Map<String, Object>> users = iPage.getRecords();

        System.out.println("总页数 :" + iPage.getPages());
        System.out.println("总记录数 :" + iPage.getTotal());
        users.forEach(System.out::println);
    }
}
