package com.zy.study.java.java8newfeatures.streamapi;

import com.zy.study.java.java8newfeatures.methodreference.entity.User;

import java.util.Arrays;
import java.util.List;

/**
 * @autho 18392
 * @date 2020/2/12
 */
public class UserList {
    public static List<User> getUserList() {
        User user1 = new User(12, "qqq", 123.0);
        User user2 = new User(13, "qwe", 234.0);
        User user3 = new User(14, "eee", 345.0);
        User user4 = new User(15, "qrrqq", 765.9);
        User user5 = new User(45, "ttttt", 999.8);
        return Arrays.asList(user1, user2, user3, user4, user5);
    }
}
