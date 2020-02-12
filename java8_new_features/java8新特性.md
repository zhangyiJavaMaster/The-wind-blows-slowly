#lambda表达式
    简单示例 : 
    语法详解及演示 ：com.zy.study.java.java8newfeatures/lambdausedemo/LambdaTest2.java
    
#函数式（Functional）接口
    /**
     * @FunctionalInterface 显示的指明这是一个函数式接口
     *      也可用来检验一个接口是否是函数时接口
     * 只有一个抽象方法
     * 只有函数式接口才能用lambda表达式创建实例
     */
    @FunctionalInterface
    public interface MyInterface {
        void method1();
    }
    
    · 在java.util.function包下定定义了JAVA8的丰富的函数式接口