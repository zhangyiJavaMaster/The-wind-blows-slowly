package com.zy.study.java.mybatisplus.until;

public class TableNameUntil {
    //创建一个本地线程用来存放数据
    public static ThreadLocal<String> myTableName = new ThreadLocal<>();
}
