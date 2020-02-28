package com.example.auctionservice.dao;

import com.example.auctionservice.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @Description: TODO
 * @Author: liyu
 * @Date: 2020/2/29
 */
@Repository
public interface UserMapper {

    boolean createUser(User user);
}
