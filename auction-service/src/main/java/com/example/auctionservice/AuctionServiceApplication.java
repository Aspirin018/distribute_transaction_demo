package com.example.auctionservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@MapperScan(value = "com.example.auctionservice.dao")
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class AuctionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuctionServiceApplication.class, args);
    }

}
