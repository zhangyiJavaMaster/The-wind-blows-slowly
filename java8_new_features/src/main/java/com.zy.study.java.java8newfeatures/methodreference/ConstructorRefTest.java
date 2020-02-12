package com.zy.study.java.java8newfeatures.methodreference;


import com.zy.study.java.java8newfeatures.methodreference.entity.User;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 一. 构造器引用
 *     和方法引用类似，函数式接口的抽象方法的形参列表和构造器的形参列表一致
 *      抽象方法的返回值即为构造器所属类的类型。
 *
 * 二. 数组引用
 *      可以把数组看作是一个特殊的类，则写法与构造器引用一致
 */

public class ConstructorRefTest {
    /*
    * 构造器引用
    * Supplier中的T get()
    * User的空参构造器
    * */
    public static void test(){
        Supplier<User> supplier1 = new Supplier<User>() {
            @Override
            public User get() {
                return new User();
            }
        } ;
        //###############################################
        Supplier<User> supplier = () -> new User();
        System.out.println("$$$$$$$$$$: " + supplier.get());
        //###############################################
        //构造器引用
        Supplier<User> supplier2 = User::new;
        System.out.println("构造器引用supplier2: " + supplier2.get());
    }

    /*
     * Function中的 R apply(T t);
     * */
    public static void test2(){
        Function<String,User> function = name -> new User(name);
        System.out.println("构造器引用function: " + function.apply("zhangsan"));

        Function<String,User> function2 = User::new;
        System.out.println("构造器引用function2: " + function2.apply("ddddd"));
    }

    /*
     * BiFunction  中的 R apply(T t, U u);
     * */
    public static void test3(){
        BiFunction<Integer, String, User> biFunction = (i, s) ->new User(i,s);
        System.out.println("构造器引用biFunction: " + biFunction.apply(1,"ddd"));

        BiFunction<Integer, String, User> biFunction2 = User::new;
        System.out.println("构造器引用biFunction2: " + biFunction2.apply(2,"ffff"));
    }

    /*
    * 数组引用
    *Function  中的 R apply(T t);
    * */
    public static void test4(){
        //Function<Integer, String> 第一个范型表示定义的数组长度
        Function<Integer, String[]> function = length -> new String[length];
        String[] arr1 = function.apply(5);
        System.out.println("lambda写法 function: " + Arrays.toString(arr1));

        Function<Integer, String[]> function2 = String[] :: new;
        String[] arr2 = function2.apply(3);
        System.out.println("数组引用 function: " + Arrays.toString(arr2));

    }

    public static void main(String[] args) {
        test();
        test2();
        test3();
        test4();
    }
}
