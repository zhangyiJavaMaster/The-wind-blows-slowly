package com.zy.study.java.java8newfeatures.optionaldemo;

import java.util.Optional;

/**
 * Optional类：为了在程序中避免出现空指针异常而创建的
 * 常用方法：
 * of(T value)
 * ofNullable(T value)
 *
 * @autho 18392
 * @date 2020/2/12
 */
public class OptionalTest {

    public static void test1() {
        System.out.println("*************Optional****************");
        /*
         * public static <T> Optional<T> of(T value)   创建一个optional实例，t必须非空
         * public static<T> Optional<T> empty()  创建一个optional实例
         * public static <T> Optional<T> ofNullable(T value)  创建一个optional实例，t可以为null
         * */
        Optional<Girl> girl = Optional.of(new Girl());

        Girl girl1 = new Girl();
        Optional<Girl> optionalgirl = Optional.ofNullable(girl1);
        System.out.println(optionalgirl);

        //orElse :如果调用该方法的optional中封装的对象是非空的，则返回调用方内部封装的数据
        //若调用方内部封装的对象为空，则返回orElse方法中的参数
        Girl girl2 = optionalgirl.orElse(new Girl("wwww"));
        System.out.println(girl2);

    }

    //#################之前写法，容易造成空指针###########
    public static String getGrilName(Boy boy) {
        return boy.getGirl().getName();
    }

    public static void test2() {
        Boy boy = new Boy();
        String grilName = getGrilName(boy);
        System.out.println(grilName);
    }
    //###############################################

    //优化后的写法
    public static String getGrilName1(Boy boy) {
        if (boy != null) {
            Girl girl = boy.getGirl();
            if (girl != null) {
                return girl.getName();
            }
        }
        return null;
    }

    public static void test3() {
        Boy boy = new Boy();
        String grilName = getGrilName1(boy);
        System.out.println(grilName);
    }
    //######################################

    //使用optional操作
    public static String getGrilName2(Boy boy) {
        Optional<Boy> optionalBoy = Optional.ofNullable(boy);
        //此时的newboy一定非空
        Boy newboy = optionalBoy.orElse(new Boy(new Girl("eeee")));
        Girl girl = newboy.getGirl();
        //先用optional将girl包起来
        Optional<Girl> optionalGirl1 = Optional.ofNullable(girl);
        //此时的girl1一定非空
        // 通过orElse 让girl1要么是girl的值，要么是orElse方法入参的值
        Girl girl1 = optionalGirl1.orElse(new Girl("fff"));

        //这样就避免了空指针
        return girl1.getName();
    }

    public static void main(String[] args) {
        test3();
    }
}
