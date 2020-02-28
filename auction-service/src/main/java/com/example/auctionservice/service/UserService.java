package com.example.auctionservice.service;

import com.example.auctionservice.entity.User;

/**
 * @Description: TODO
 * @Author: liyu
 * @Date: 2020/2/29
 */
public interface UserService {

    boolean createUser(User user);

    boolean updateUser(Long userId);
}
