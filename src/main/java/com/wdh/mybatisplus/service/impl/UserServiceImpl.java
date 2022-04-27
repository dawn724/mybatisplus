package com.wdh.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wdh.mybatisplus.mapper.UserMapper;
import com.wdh.mybatisplus.pojo.User;
import com.wdh.mybatisplus.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional()
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
