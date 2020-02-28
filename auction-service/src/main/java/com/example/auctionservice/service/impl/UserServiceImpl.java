package com.example.auctionservice.service.impl;

import com.example.auctionservice.dao.UserMapper;
import com.example.auctionservice.entity.User;
import com.example.auctionservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: TODO
 * @Author: liyu
 * @Date: 2020/2/29
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;


    @Override
    public boolean createUser(User user) {
        return userMapper.createUser(user);
    }
}
