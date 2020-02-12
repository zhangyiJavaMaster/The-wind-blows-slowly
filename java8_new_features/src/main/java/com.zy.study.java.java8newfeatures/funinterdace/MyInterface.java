package com.zy.study.java.java8newfeatures.funinterdace;
/**
 * @FunctionalInterface 显示的指明这是一个函数式接口
 *      也可用来检验一个接口是否是函数时接口
 * 只有一个抽象方法
 * 只有函数式接口才能用lambda表达式创建实例
 *
 * 在java.util.function包下定定义了JAVA8的丰富的函数式接口
 */
@FunctionalInterface
public interface MyInterface {
    void method1();
}
