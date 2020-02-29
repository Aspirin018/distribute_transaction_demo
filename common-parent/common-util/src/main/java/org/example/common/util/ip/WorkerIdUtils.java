package org.example.common.util.ip;

import java.net.InetAddress;

/**
 * @Description: 初始化工作ID
 * @Author: liyu
 * @Date: 2020/2/29
 */
public class WorkerIdUtils {

    public static long initWorkerId(){
        InetAddress inetAddress = IpUtils.getPlatformInetAddress();
        if(inetAddress == null) {
            throw new RuntimeException("获取不到网络接口信息");
        }
        byte[] bytes = inetAddress.getAddress();
        long workId = 0L;

        if(bytes.length == 4){
            for(byte byteNum : bytes){
                workId += byteNum & 0xFF;
            }
        } else if(bytes.length == 16) {
            for(byte byteNum : bytes) {
                workId += byteNum & 0B111111;
            }
        } else {
            throw new IllegalStateException("Bad InetAddress, please check your network.");
        }
        return workId;
    }
}
