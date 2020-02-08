package com.zy.study.java.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.study.java.mybatisplus.entity.User;
import com.zy.study.java.mybatisplus.mapper.UserMapper;
import com.zy.study.java.mybatisplus.service.MpService;
import org.springframework.stereotype.Service;

@Service
public class MpServiceImpl extends ServiceImpl<UserMapper, User>
        implements MpService {
}
