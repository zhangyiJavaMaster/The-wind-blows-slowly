package com.zy.study.java.java8newfeatures.methodreference;

import com.zy.study.java.java8newfeatures.methodreference.entity.User;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 1. 使用情景：当要传递给lambda体的操作，已经有实现的方法，可以使用方法引用
 *
 * 2. 方法引用本质上就是lambda表达式，而lambda表达式作为函数式接口的实例，所以
 *      方法引用也是函数式接口的实例
 * 3. 使用格式： 类/对象(方法的调用者)::方法名
 * 4. 具体分为如下三种情况：
 *      情况1  对象::非静态方法
 *      情况2  类::静态方法
 *      情况3  类::非静态方法
 * 5.方法引用使用的要求： 传递给函数式接口中函方法的操作方法的 形参列表和返回值类型得和
 *      函数式接口中的方法的形参列表和返回值相同（主要针对情况1，2）
 */
public class MethodReferenceTest {
    /*
    * 情况1：
    * Consumer中的void accept(T t)
    * PrintStream中的void println(T t)
    * 两个方法的返回值和形参相同，传递给accept的打印操作的实现方法是println
    * */
    public static void test1(){
        Consumer<String> consumer = str -> System.out.println(str);
        consumer.accept("lambda表达式");

        System.out.println("************************");
        PrintStream pt = System.out;
        //给Consumer的accept方法中传递的是PrintStream对象的println方法
        Consumer<String> consumer1 = pt::println;
        //Consumer<String> consumer1 =System.out::println;
        consumer1.accept("方法引用");
    }

    /*
    * Supplier中的T get()和User中的getName 的方法样子类似，无参，有返回值
    * 传递给T get() 的user对象的获取名字的方法已经有实现了
    * */
    public static void test2(){
        User user = new User();
        user.setName("Supplier lambda实现");
        Supplier<String> stringSupplier = () -> user.getName();
        System.out.println(stringSupplier.get());

        user.setName("Supplier 方法引用用实现");
        //给Supplier的get方法中 传递的是User对象的getName方法
        Supplier<String> stringSupplier2 = user::getName;
        System.out.println(stringSupplier.get());

    }

    /*
    * 情况2： 类::静态方法
    * Comparator中的int compare(T o1, T o2);
    * Integer中的int compare(int x, int y)
    * */
    public static void test3(){
        System.out.println("情况2： 类::静态方法");
        Comparator<Integer> comparator = (t1,t2)->Integer.compare(t1,t2);
        System.out.println(comparator.compare(1,2));

        Comparator<Integer> comparator1 = Integer::compare;
        System.out.println(comparator1.compare(2,1));
    }

    /*
     * Function 中的R apply(T t);
     * Math 中的public static long round(double a)
     * */
    public static void test4(){
        Function<Double,Long> function = t1-> Math.round(t1);
        System.out.println("function: " +function.apply(3.12));

        Function<Double,Long> function2 = Math::round;
        System.out.println("function2: " +function.apply(53.1));
    }

    /*
    * 情况3： 类::实例方法(有难度)
    * Comparator 中的 int compare(T o1, T o2);
    * String中的o1.compareTo(o2)
    * */
    public static void test5(){
        //第一个参数作为方法的调用者，第二个参数作为方法从入参
        //写方法引用的时候可以用类名
        Comparator<String> comparator = (s1,s2) -> s1.compareTo(s2);
        System.out.println("情况3: " + comparator.compare("qwe","wqe"));

        Comparator<String> comparator2 = String::compareTo;
        System.out.println("情况3:方法引用: " + comparator2.compare("qwe","wqe"));
    }

    /*
     * BiPredicate 中的 boolean test(T t, U u);
     * String中的 public boolean equals(Object anObject)
     * */
    public static void test6(){
        BiPredicate<String,String> biPredicate = (s1,s2) ->s1.equals(s2);
        System.out.println("情况3 biPredicate: " + biPredicate.test("qwe","wqe"));

        BiPredicate<String,String> biPredicate2 = String::equals;
        System.out.println("情况3 biPredicate: " + biPredicate2.test("qwe","wqe"));
    }

    /*
     * BiPredicate 中的 boolean test(T t, U u);
     * User  中的 String getName()
     * */
    public static void test7(){
        User user = new User();
        user.setName("王八蛋");
        Function<User, String> function = u -> u.getName();
        System.out.println("情况3 function: " + function.apply(user));

        Function<User, String> function2 = User::getName;
        System.out.println("情况3 function2: " + function2.apply(user));
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
        test7();
    }
}
