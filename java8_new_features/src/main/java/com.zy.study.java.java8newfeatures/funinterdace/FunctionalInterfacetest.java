package com.zy.study.java.java8newfeatures.funinterdace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * 函数式接口：若一个接口只声明了一个抽象方法，则此接口就称为函数式接口
 * <p>
 * Java内置4大核心函数是接口
 * 消费型接口 Consumer<T>  包含函数    void accept(T t);
 * 供给型接口 Supplier<T>  包含函数    T get();
 * 函数型接口 Function<T, R> 包含函数  R apply(T t);
 * 断定型接口 Predicate<T>  包含函数   boolean test(T t);
 */

public class FunctionalInterfacetest {
    public static void main(String[] args) {
        /*
         * 消费示例
         * */
        //调用方式1
        consumerMoney(500, new Consumer<Double>() {
            @Override
            public void accept(Double aDouble) {
                System.out.println("匿名函数类实现消费金额：" + aDouble);
            }
        });

        //调用方式2
        consumerMoney(10, m1 -> System.out.println("lambda方式消费：" + m1));

        /*
         * 字符串过滤实例
         * */
        //过滤字符串，返回长度大小于4的字符串集合
        List<String> list1 = filter(Arrays.asList("123", "4567", "345"), new Predicate<String>() {
            @Override
            public boolean test(String s) {
                if (s.length() < 4) {
                    return true;
                }
                return false;
            }
        });
        list1.forEach(System.out::println);

        //过滤字符串，返回含有张字的字符串集合
        List<String> list2 = filter(Arrays.asList("张三", "张无忌", "王小二"), s -> s.contains("张"));
        list2.forEach(System.out::println);

    }

    //该方法是用来消费money，具体消费的规则由调用者提供
    public static void consumerMoney(double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }

    //该方法用来批量过滤字符串，然后把符合条件的字符串返回，字符串的过滤条件由调用者提供
    public static List<String> filter(List<String> list, Predicate<String> predicate) {
        ArrayList<String> result = new ArrayList<>(32);
        for (String s : list) {
            if (predicate.test(s)) {
                result.add(s);
            }
        }
        return result;
    }
}
