package com.example.auctionservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description: TODO
 * @Author: liyu
 * @Date: 2020/2/29
 */
@Data
@AllArgsConstructor
public class User {

    private String name ;
    private String sex;
    private Integer age;
    private Integer classNo;
}
