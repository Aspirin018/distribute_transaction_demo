package com.example.auctionservice.interceptor;

import com.example.auctionservice.remoteService.TokenService;
import org.example.common.db.redis.JedisTemplate;
import org.example.common.db.redis.ThreadMsgCacheKey;
import org.example.common.db.userMBservice.UseMBCacheController;
import org.example.common.db.userMBservice.UseMBController;
import org.example.common.util.ip.IpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * @Description: TODO
 * @Author: liyu
 * @Date: 2020/2/29
 */
@Component
public class ControllerEnhanceHandler implements HandlerInterceptor {

    public static final String APPLICATIONNAME = "applicationName";
    public static final String METHODNAME = "methodName";
    public static final String MBTOKEN = "MBToken";
    private static final Logger logger = LoggerFactory.getLogger(ControllerEnhanceHandler.class);
    private JedisTemplate jedisTemplate;
    private TokenService tokenService;
    private Environment environment;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        jedisTemplate = factory.getBean(JedisTemplate.class);
        tokenService = factory.getBean(TokenService.class);
        environment = factory.getBean(Environment.class);

        HandlerMethod method = null;
        if (handler instanceof HandlerMethod) {
            method = (HandlerMethod) handler;
        }

        if (method != null) {
            //ip + 线程名 + 线程ID
            String currentThreadName = IpUtils.getPlatformIp() + Thread.currentThread().getName() + Thread.currentThread().getId();
            ThreadMsgCacheKey threadMsgCacheKey = new ThreadMsgCacheKey(currentThreadName);
            //请求中的 applicationName methodName MBToken
            HashMap<String, String> hashMap = getValueFromRequest(request);
            if(hashMap != null){
                //带有三个值
                logger.info("进入web拦截器,请求参数中包含token信息");
                //value为req中 工程名称+方法名称+token  以:分隔
                StringBuffer buffer = new StringBuffer();
                buffer.append(hashMap.get(APPLICATIONNAME)).append(":").append(hashMap.get(METHODNAME)).append(":").append(hashMap.get(MBTOKEN));
                jedisTemplate.put(threadMsgCacheKey,buffer.toString());
            }else {
                //不带有三个值
                logger.info("进入web拦截器,请求参数中不包含token信息");
                //事务注解
                UseMBController annotation = method.getMethod().getAnnotation(UseMBController.class);
                //命令策注解
                UseMBCacheController cacheAnnotation = method.getMethod().getAnnotation(UseMBCacheController.class);
                if(annotation != null && cacheAnnotation != null){
                    throw  new RuntimeException("不支持controller同时拥有两种注解方法,请进行拆分");
                }
                if(annotation == null && cacheAnnotation == null){
                    //没有注解 不进行操作
                    //logger.info("请求中不包含token信息,controller方法上无注解不进行操作");
                }else if(annotation != null) {
                    //logger.info("获取事务token");
                    hasAnnotation(method, threadMsgCacheKey);
                } else if(cacheAnnotation != null) {
                    //logger.info("获取命令策token");
                    hasAnnotation(method, threadMsgCacheKey);
                }
            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    private void hasAnnotation(HandlerMethod method, ThreadMsgCacheKey threadMsgCacheKey) {
        //获取token
        String token = tokenService.getToken();
        logger.info("当前方法:{},获得的token为:{}",method.getMethod().getName(),token);
        String applicationName = environment.getProperty("spring.application.name");
        //value为工程名称+方法名称+token  以:分隔
        StringBuffer buffer = new StringBuffer();
        buffer.append(applicationName).append(":").append(method.getMethod().getName()).append(":").append(token);
        jedisTemplate.put(threadMsgCacheKey,buffer.toString());
    }

    private HashMap<String, String> getValueFromRequest(HttpServletRequest request) {
        String applicantionNameValue = request.getHeader(APPLICATIONNAME);
        String methodeNameValue = request.getHeader(METHODNAME);
        String tokenvalue = request.getHeader(MBTOKEN);
        if (applicantionNameValue != null && !applicantionNameValue.isEmpty() && methodeNameValue != null
                && !methodeNameValue.isEmpty() && tokenvalue != null && !tokenvalue.isEmpty()) {
            HashMap<String, String> hashMap = new HashMap<>(3);
            hashMap.put(APPLICATIONNAME, applicantionNameValue);
            hashMap.put(METHODNAME, methodeNameValue);
            hashMap.put(MBTOKEN, tokenvalue);
            return hashMap;
        }
        return null;
    }

}
