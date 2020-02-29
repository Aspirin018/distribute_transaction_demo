package org.example.common.util.ip;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * @Description: TODO
 * @Author: liyu
 * @Date: 2020/2/29
 */
public class IpUtils {

    public static InetAddress getPlatformInetAddress(){
        Enumeration<NetworkInterface> networkInterfaceEnum = null;
        try{
            networkInterfaceEnum = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaceEnum.hasMoreElements()){
                NetworkInterface networkInterface = networkInterfaceEnum.nextElement();
                Enumeration<InetAddress> inetAddressEnum = networkInterface.getInetAddresses();
                while(inetAddressEnum.hasMoreElements()){
                    InetAddress inetAddress = inetAddressEnum.nextElement();
//                    String ip = inetAddress.getHostAddress();
                    return inetAddress;
                }
            }
        }catch(SocketException e){
            throw new RuntimeException(e);
        }
        return null;
    }

    public static String getPlatformIp(){
        InetAddress inetAddress = getPlatformInetAddress();
        if (inetAddress != null){
            return  inetAddress.getHostAddress();
        }
        return "127.0.0.1";
    }
}
