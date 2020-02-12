package com.zy.study.java.java8newfeatures.lambdausedemo;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * Lambda 用法
 * 1.举例： (o1, o2) -> Integer.compare(o1, o2);
 * 2.格式：
 *       -> ：lambda操作符 或 箭头操作符
 *       -> 左边 ：lambda形参列表，其实就是接口的匿名实现类中被覆写方法的形参列表
 *       -> 右边 ：lambda体，其实就是接口匿名实现类中被覆写方法的方法体
 * 3.Lambda表达式的使用：（分六种情况）
 *   1）无参，无返回值
 *   2) lambda需要一个参数，无返回值
 *   3) 数据类型可以省略，因为可以由编译器推断得出，称为"类型推断"
 *   4) lambda需要两个或者两个以上参数，多条执行语句，并且可以有返回值。
 *   5) lambda需要两个或者两个以上参数，多条执行语句，并且可以有返回值
 *   6) 当lambda体只有一条语句时，return与大括号若有，都可以省略
 *
 *   总结：
 *      -> 左边 ： lambda形参列表的参数类型可以省略（类型推断）；
 *                若lambda的形参列表只有一个参数，其一对()也可以省略；
 *                若lambda的形参列表没有参数，或者多个参数，其()就不要省略
 *
 *      -> 右边 ：lambda体应该使用一对{}包裹；
 *      若lambda体只有一条执行语句（可能是return语句），则可以省略{}以及return关键字
 *
 * 4.Java中lambda表达式的本质： 作为函数式接口的一个实例
 *
 * 5.函数式接口：若一个接口只声明了一个抽象方法，则此接口就称为函数式接口
 *
 */
public class LambdaTest2 {
    //语法格式1：无参，无返回值
    public static void test1(){
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("匿名接口方式");
            }
        };

        r1.run();
        System.out.println("****************************");
        //()->System.out.println("lambda表达式") 作为 Runnable 接口的一个实例
        Runnable r2 = ()->System.out.println("lambda表达式");
        r2.run();
    }

    //语法格式2：lambda需要一个参数，无返回值
    public static void test2(){
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        consumer.accept("一个参数，无返回值");

        System.out.println("***************************");
        Consumer<String> consumer1 = s -> System.out.println(s);
        Consumer<String> consumer2 = (String s) -> {
            System.out.println(s);
        };
        consumer1.accept("lambda写法:省略类型");
        consumer2.accept("lambda写法：不省略类型");
    }

    //语法格式3： 数据类型可以省略，因为可以由编译器推断得出，称为"类型推断"
    //语法格式4： lambda只需要一个参数时，参数的小括号都可以省略
    public static void test3(){

        Consumer<String> consumer2 = (String s) -> {
            System.out.println(s);
        };
        consumer2.accept("lambda写法：不省略类型");

        Consumer<String> consumer1 = s -> System.out.println(s);
        consumer1.accept("lambda写法:省略类型");
    }

    //语法格式5： lambda需要两个或者两个以上参数，多条执行语句，并且可以有返回值。
    public static void test4(){
        Comparator<Integer> comparator1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                System.out.println("入参1: " + o1);
                System.out.println("入参2: " + o2);
                return Integer.compare(o1, o2);
            }
        };
        int res1 = comparator1.compare(1,2);
        System.out.println(res1);

        System.out.println("*********************");

        //lambda 表达式写法
        Comparator<Integer> comparator2 = (o1, o2) -> {
            System.out.println("lambda 表达式写法 入参1: " + o1);
            System.out.println("lambda 表达式写法 入参2: " + o2);
            return Integer.compare(o1, o2);
        };
        int res2 = comparator2.compare(3,2);
        System.out.println(res2);
    }

    //语法格式6： 当lambda体只有一条语句时，return与大括号若有，都可以省略。
    public static void test5(){
        Comparator<Integer> comparator1 = (o1, o2) -> {
            return Integer.compare(o1, o2);
        };
        int res1 = comparator1.compare(3,2);
        System.out.println(res1);

        System.out.println("*********************");

        //编译器能够推断出唯一结果的都可以省略
        Comparator<Integer> comparator2 = (o1, o2) -> Integer.compare(o1, o2);
        int res2 = comparator2.compare(3,2);
        System.out.println(res2);
    }


    public static void main(String[] args) {
        test5();
    }

}
