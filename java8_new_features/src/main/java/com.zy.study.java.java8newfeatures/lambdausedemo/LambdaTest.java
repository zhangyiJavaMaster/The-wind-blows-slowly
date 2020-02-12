package com.zy.study.java.java8newfeatures.lambdausedemo;

import java.util.Comparator;

/*
* lanbda 表达式使用举例
* */
public class LambdaTest {
    public static void main(String[] args) {
        test1();
        test2();
    }

    /*
    * 带参数的
    * */
    private static void test2() {
        //匿名函数写法
        Comparator<Integer> comparator1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        int res1 = comparator1.compare(1,2);
        System.out.println(res1);

        System.out.println("*********************");

        //lambda 表达式写法
        Comparator<Integer> comparator2 = (o1, o2) -> Integer.compare(o1, o2);
        int res2 = comparator2.compare(3,2);
        System.out.println(res2);

        System.out.println("*********************");

        //方法引用写法
        Comparator<Integer> comparator3 = Integer::compare;
        int res3 = comparator3.compare(3,2);
        System.out.println(res3);
    }

    /*
    * 不带参数的
    * */
    private static void test1() {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("匿名接口方式");
            }
        };

        r1.run();
        System.out.println("****************************");

        Runnable r2 = ()->System.out.println("lambda表达式");
        r2.run();

    }
}
