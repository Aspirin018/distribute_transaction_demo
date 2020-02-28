package com.example.messagebroker.strategy;

/**
 * @Description: TODO
 * @Author: liyu
 * @Date: 2020/2/29
 */
public class KeyGeneratorFactory {

    private static final DefaultKeyGenerator instance = new DefaultKeyGenerator();

    public static DefaultKeyGenerator getInstance() {
        return instance;
    }
}
