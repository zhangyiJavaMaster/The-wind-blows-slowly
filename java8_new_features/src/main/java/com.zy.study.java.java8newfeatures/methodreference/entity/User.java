package com.zy.study.java.java8newfeatures.methodreference.entity;

import lombok.Data;

@Data
public class User {
    private String name;
    private int age;
    private Double salary;

    public User() {

    }

    public User(String name) {
        this.name = name;
    }

    public User(int age, String name) {
        this.name = name;
        this.age = age;
    }

    public User(int age, String name, Double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }
}
