package com.zy.study.java.java8newfeatures.streamapi;

import com.zy.study.java.java8newfeatures.methodreference.entity.User;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Stream 的终止操作
 *
 * @autho 18392
 * @date 2020/2/12
 */
public class StreamEndOmTest {
    //1-匹配与查找
    public static void test1() {
        //构建原始数据
        List<User> userList = UserList.getUserList();
        System.out.println("*************allMatch****************");
        /*
         *boolean allMatch(Predicate<? super T> predicate);
         * 检查是否匹配所有元素
         * */
        //是否所有员工的年龄都大于18岁
        boolean b = userList.stream().allMatch(user -> user.getAge() > 18);
        System.out.println(b);

        System.out.println("*************anyMatch****************");
        /*
         *boolean anyMatch(Predicate<? super T> predicate);
         * 检查只要有一个满足条件
         * */
        //是否有员工的年龄大于30岁
        boolean b1 = userList.stream().anyMatch(user -> user.getAge() > 30);
        System.out.println(b1);
        System.out.println("*************noneMatch****************");
        /*
         *boolean noneMatch(Predicate<? super T> predicate);
         * 检查是否没有匹配的元素
         * */
        //是否存在员工姓雷
        boolean b2 = userList.stream().noneMatch(user -> user.getName().startsWith("雷"));
        System.out.println(b2);
        System.out.println("*************findFirst****************");
        /*
         *Optional<T> findFirst();
         * 查找第一个元素
         * */
        //是否存在员工姓雷
        Optional<User> first = userList.stream().findFirst();
        System.out.println(first);
        System.out.println("*************findAny****************");
        /*
         *Optional<T> findAny();
         * 查找任意一个元素
         * */
        //是否存在员工姓雷
        Optional<User> any = userList.parallelStream().findAny();
        System.out.println(any);
        System.out.println("*************count****************");
        /*
         * long count();
         * 返回流中元素的总个数
         * */
        long count = userList.parallelStream().filter(user -> user.getSalary() > 100).count();
        System.out.println(count);

        System.out.println("*************max****************");
        /*
         * Optional<T> max(Comparator<? super T> comparator);
         * 返回流中最大值
         * */
        //返回最高的工作  流-》工资流-》取最大值
        Optional<Double> max = userList.stream().map(User::getSalary).max(Double::compareTo);
        Optional<Double> max2 = userList.stream().map(user -> user.getSalary()).max(Double::compareTo);
        System.out.println(max + "   " + max2);
        System.out.println("*************min****************");
        /*
         * Optional<T> min(Comparator<? super T> comparator);
         * 返回流中最小值
         * */
        //返回最低工资的员工
        Optional<User> min = userList.stream().min((user1, user2) -> Double.compare(user1.getSalary(), user2.getSalary()));
        Optional<User> min2 = userList.stream().min(Comparator.comparingDouble(User::getSalary));
        System.out.println(min + "   " + min2);

        System.out.println("*************forEach****************");
        /*
         * void forEach(Consumer<? super T> action);
         * 内部迭代
         * */
        userList.stream().forEach(System.out::println);

        //使用集合的遍历操作
        userList.forEach(System.out::println);

    }


    //2-规约
    public static void test2() {

        System.out.println("*************reduce****************");
        /*
         *  T reduce(T identity, BinaryOperator<T> accumulator);
         * 可以将流中元素反复结合起来，得到一个值,返回T
         * */
        //计算1-10自然数的和
        Integer reduce = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).stream().reduce(0, Integer::sum);
        System.out.println(reduce);

        System.out.println("*************reduce2****************");
        /*
         *  Optional<T> reduce(BinaryOperator<T> accumulator);
         * 可以将流中元素反复结合起来，等到一个值，返回Optional<T>
         * */
        //计算公司所有员工工资的综合
        Optional<Double> reduce1 = UserList.getUserList().stream().map(User::getSalary).reduce(Double::sum);
        //不知道Double中的sum方法，自己求和写法
        Optional<Double> reduce3 = UserList.getUserList().stream().map(User::getSalary).reduce((d1, d2) -> d1 + d2);
        System.out.println(reduce1 + "       " + reduce3);
    }

    //3-收集
    public static void test3() {

        System.out.println("*************collect****************");
        /*
         *  <R, A> R collect(Collector<? super T, A, R> collector);
         * 将流转换成其他形式，接收一个Collector接口实现，用于给stream中元素做汇总
         * Collector 接口中方法的实现决定了如何对流执行收集的操作（如收集到List，Set，Map等）
         * 另外，Collectors类中提供了很多静态方法，可以方便的创建场景的收集器实例。
         * */
        //查找工资大于200的员工，将结果返回成一个List或者Set
        List<User> collect = UserList.getUserList().stream().filter(user -> user.getSalary() > 200).collect(Collectors.toList());
        collect.forEach(System.out::println);

        Set<User> collect1 = UserList.getUserList().stream().filter(user -> user.getSalary() > 200).collect(Collectors.toSet());
        collect1.forEach(System.out::println);
    }

    public static void main(String[] args) {
        test3();
    }
}
