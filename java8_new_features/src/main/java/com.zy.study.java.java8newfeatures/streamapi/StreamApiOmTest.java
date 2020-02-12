package com.zy.study.java.java8newfeatures.streamapi;

import com.zy.study.java.java8newfeatures.methodreference.entity.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 测试stream的中间操作
 *
 * @autho 18392
 * @date 2020/2/12
 */
public class StreamApiOmTest {

    //1-筛选与切片
    public static void test1() {
        //构建原始数据
        List<User> userList = UserList.getUserList();
        System.out.println("*************filter****************");
        /*
         *Stream<T> filter(Predicate<? super T> predicate)
         * 接收lambda，从流中排除某些元素
         * */
        Stream<User> userStream = userList.stream();
        //从流中排除年龄小于14的人,及查询年龄大于14的人
        //.forEach(System.out::println) 这是终止操作，会吧流关闭
        userStream.filter(user -> user.getAge() > 14).forEach(System.out::println);
        System.out.println("***********limit******************");
        /*
         * Stream<T> limit(long maxSize);
         * 截断流，使其元素不超过给定数量
         * */
        userList.stream().limit(2).forEach(System.out::println);
        System.out.println("************skip*****************");
        /*
         * Stream<T> skip(long n);
         * 跳过元素，返回一个扔掉了前n个元素的流，若流中元素不足n个，就返回一个空流
         * */
        userList.stream().skip(3).forEach(System.out::println);
        System.out.println("***********distinct******************");
        /*
         * Stream<T> distinct();
         * 筛选，通过流所生成元素的hashCode()和eauqls()去除重复元素
         * 需要覆写对象的hashCode()和eauqls()方法
         * */
        userList.stream().distinct().forEach(System.out::println);
    }

    //2-映射
    public static void test2() {
        System.out.println("***********map******************");
        /*
         * <R> Stream<R> map(Function<? super T, ? extends R> mapper);
         * 接收一个函数作为参数，将元素转换成其他形式或提取信息，
         * 该函数函数会被应用到每一个元素上，并将器其映射成一个新元素
         * */
        Arrays.asList("a", "cv", "f").stream().map(str -> str.toUpperCase())
                .forEach(System.out::println);
        //练习1：获取员工姓名大于3的员工的姓名
        //从员工列表中获取姓名的stream
        UserList.getUserList().stream().map(user -> user.getName())
                //过滤nameStream
                .filter(name -> name.length() > 3)
                .forEach(System.out::println);
        //练习2：
        System.out.println("**练习2****");
        Stream<Stream<Character>> streamStream = Arrays.asList("aa", "cc", "ff").stream()
                .map(StreamApiOmTest::fromStringToString);
        streamStream.forEach(s -> {
            s.forEach(System.out::println);
        });

        System.out.println("***********flatMap******************");
        /*
         * <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper);
         * 接收一个函数作为参数，将流中的每一个值都换成一个流，
         * 然后把所有的流连成一个流
         * 两个list做运算：
         * map类似于list1.add(list2);
         * flaMap类似于list1.addAll(list2)；
         * */
        Stream<Character> characterStream = Arrays.asList("aa", "cc", "ff").stream()
                .flatMap(StreamApiOmTest::fromStringToString);
        characterStream.forEach(System.out::println);
    }

    //将字符串中的多个字符串构成的集合转换为对应的stream实例
    public static Stream<Character> fromStringToString(String string) {
        ArrayList<Character> result = new ArrayList<>();
        for (Character c : string.toCharArray()) {
            result.add(c);
        }
        return result.stream();
    }

    //3-排序
    public static void test3() {
        System.out.println("*****sorted自然排序**************");
        /*
         * Stream<T> sorted();自然排序
         * */
        Arrays.asList(1, 3, 5, 7, 4, 2).stream().sorted().forEach(System.out::println);
        System.out.println("*****sorted 定制排序**************");
        /*
         * Stream<T> sorted(Comparator<? super T> comparator);
         * 定制排序
         * */
        //按年龄排序
        System.out.println("*****sorted 按年龄排序**************");
        UserList.getUserList().stream()
                .sorted((u1, u2) -> Integer.compare(u1.getAge(), u2.getAge()))
                .forEach(System.out::println);
        //先按年龄排序，年龄相同，按工资排序
        System.out.println("*****sorted 先按年龄排序，年龄相同，按工资排序**************");
        UserList.getUserList().stream()
                .sorted((u1, u2) -> {
                    int boo = Integer.compare(u1.getAge(), u2.getAge());
                    if (boo != 0) {
                        return boo;
                    } else {
                        return Double.compare(u1.getSalary(), u2.getSalary());
                    }
                })
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        //test1();
        test3();
    }
}
