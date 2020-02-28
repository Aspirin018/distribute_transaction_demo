package com.example.auctionservice.remoteService;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: TODO
 * @Author: liyu
 * @Date: 2020/2/29
 */
@FeignClient("message-broker")
public interface TokenService {

    @GetMapping("/token/getToken")
    String getToken();
}
