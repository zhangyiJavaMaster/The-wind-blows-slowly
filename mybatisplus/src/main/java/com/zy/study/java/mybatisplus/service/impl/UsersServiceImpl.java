package com.zy.study.java.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.zy.study.java.mybatisplus.entity.Users;

import com.zy.study.java.mybatisplus.mapper.UsersMapper;
import com.zy.study.java.mybatisplus.service.UsersService;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users>
        implements UsersService {
}
