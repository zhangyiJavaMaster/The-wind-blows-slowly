package com.zy.study.java.java8newfeatures.optionaldemo;

import lombok.Data;

/**
 * @autho 18392
 * @date 2020/2/12
 */
@Data
public class Boy {
    private Girl girl;

    public Boy(Girl girl) {
        this.girl = girl;
    }

    public Boy() {
    }
}
