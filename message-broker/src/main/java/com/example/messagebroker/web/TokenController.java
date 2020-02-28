package com.example.messagebroker.web;

import com.example.messagebroker.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: TODO
 * @Author: liyu
 * @Date: 2020/2/29
 */
@RequestMapping("/token")
@RestController
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @GetMapping("/getToken")
    public String getToken() {
        return tokenService.createToken();
    }
}
