package com.example.messagebroker.service.impl;

import com.example.messagebroker.service.TokenService;
import com.example.messagebroker.strategy.DefaultKeyGenerator;
import com.example.messagebroker.strategy.KeyGeneratorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Description: TODO
 * @Author: liyu
 * @Date: 2020/2/29
 */
@Service
public class TokenServiceImpl implements TokenService {

    private static final Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);
    @Override
    public String createToken() {
        DefaultKeyGenerator generator = KeyGeneratorFactory.getInstance();
        String token = generator.generateKey().toString();
        logger.info("createToken token: " + token);
        return token;
    }
}
