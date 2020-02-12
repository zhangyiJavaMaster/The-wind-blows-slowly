package com.zy.study.java.java8newfeatures.optionaldemo;

import lombok.Data;

/**
 * @autho 18392
 * @date 2020/2/12
 */
@Data
public class Girl {
    private String name;
    private int age;

    public Girl(String name) {
        this.name = name;
    }

    public Girl() {
    }
}
