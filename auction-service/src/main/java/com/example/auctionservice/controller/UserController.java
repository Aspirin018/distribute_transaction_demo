package com.example.auctionservice.controller;

import com.example.auctionservice.entity.User;
import com.example.auctionservice.service.UserService;
import org.example.common.util.web.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: TODO
 * @Author: liyu
 * @Date: 2020/2/29
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    public ResponseResult createUser(User user){
        return userService.createUser(user) ? ResponseResult.success() : ResponseResult.failed();
    }
}
