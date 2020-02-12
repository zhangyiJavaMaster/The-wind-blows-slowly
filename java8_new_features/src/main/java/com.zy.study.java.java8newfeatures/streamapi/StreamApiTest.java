package com.zy.study.java.java8newfeatures.streamapi;

import com.zy.study.java.java8newfeatures.methodreference.entity.User;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @autho 18392
 * @date 2020/2/12
 * <p>
 * 1. stream 关注的是对数据的运算，与CPU打交道
 * 集合关注的是数据存储，与内存打交道
 * <p>
 * 2.stream的特点
 * · stream自己不会存储元素
 * · stream不会改变源对象，相反，他会返回一个有特定结果的新stream
 * · stream操作是延迟执行的，这意味着它会等到需要结果的时候才执行
 * <p>
 * 3. stream的执行流程
 * · stream的实例化
 * · 一系列的中间操作（过滤，映射， 。。。）
 * · 终止操作
 * <p>
 * 4.说明
 * · 一个中间操作链，对数据源的数据进行处理
 * · 一旦执行终止操作，就执行中间操作链，并产生结果，之后不会再被使用
 * <p>
 * 5.创建stream
 * 5.1 方式一，通过集合创建
 */
public class StreamApiTest {
    //创建stream方式1：通过集合创建 Collection<E> 接口中的方法
    public static void test() {

        List<User> userList = UserList.getUserList();
        //default Stream<E> stream(); 返回一个顺序流
        Stream<User> userStream = userList.stream();
        //default Stream<E> parallelStream();  返回一个并行流
        Stream<User> parallelStream = userList.parallelStream();
    }

    //创建stream方式2： 通过数组创建
    public static void test2() {
        //基础类型数据的数组创建流
        int[] arr = new int[]{1, 2, 3, 4};
        //调用Arrays类中的public static <T> Stream<T> stream(T[] array)：返回一个流
        IntStream intStream = Arrays.stream(arr);

        //自定义的数据类型数组创建流
        User user1 = new User(12, "qqq");
        User user2 = new User(13, "qwe");
        User[] arr1 = new User[]{user1, user2};
        Stream<User> userStream = Arrays.stream(arr1);
    }

    //创建stream方式3： 通过数组创建Stream的of()方法
    public static void test3() {
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4);
    }

    //创建stream方式4： 创建无线流
    public static void test4() {
        //迭代
        //public static<T> Stream<T> iterate(final T seed, final UnaryOperator<T> f)
        //便利前10个偶数
        Stream.iterate(0, t -> t + 2).limit(10).forEach(System.out::println);

        //生成
        //public static<T> Stream<T> generate(Supplier<T> s)
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }

    public static void main(String[] args) {
        test4();
    }
}
